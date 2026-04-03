package edu.cnm.deepdive.pixelcreate

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import kotlin.math.floor

class PixelCanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val gridSize = 32

    private val layerManager = LayerManager(gridSize)

    private val gridPaint = Paint().apply {
        color = Color.LTGRAY
        style = Paint.Style.STROKE
        strokeWidth = 1f
    }

    private val fillPaint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = false
    }

    var currentColor: Int = Color.BLACK

    // Zoom & pan
    private var scaleFactor = 1f
    private var offsetX = 0f
    private var offsetY = 0f

    private val scaleDetector = ScaleGestureDetector(context, ScaleListener())
    private val panDetector = GestureDetector(context, PanListener())

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()

        canvas.translate(offsetX, offsetY)
        canvas.scale(scaleFactor, scaleFactor)

        val cellWidth = width / gridSize.toFloat()
        val cellHeight = height / gridSize.toFloat()

        // Draw all visible layers, bottom to top
        for (layer in layerManager.getLayers()) {
            if (!layer.visible) continue

            fillPaint.alpha = (layer.opacity * 255).toInt()

            for (row in 0 until gridSize) {
                for (col in 0 until gridSize) {
                    val color = layer.pixels[row][col]
                    if (color != Color.TRANSPARENT) {
                        fillPaint.color = color
                        canvas.drawRect(
                            col * cellWidth,
                            row * cellHeight,
                            (col + 1) * cellWidth,
                            (row + 1) * cellHeight,
                            fillPaint
                        )
                    }
                }
            }
        }

        // Grid on top
        gridPaint.alpha = 255
        for (i in 0..gridSize) {
            canvas.drawLine(i * cellWidth, 0f, i * cellWidth, gridSize * cellHeight, gridPaint)
            canvas.drawLine(0f, i * cellHeight, gridSize * cellWidth, i * cellHeight, gridPaint)
        }

        canvas.restore()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        scaleDetector.onTouchEvent(event)
        panDetector.onTouchEvent(event)

        // Two fingers = zoom/pan only
        if (event.pointerCount > 1) return true

        // One finger = draw
        if (event.action == MotionEvent.ACTION_DOWN ||
            event.action == MotionEvent.ACTION_MOVE
        ) {
            val cellWidth = width / gridSize.toFloat()
            val cellHeight = height / gridSize.toFloat()

            val canvasX = (event.x - offsetX) / scaleFactor
            val canvasY = (event.y - offsetY) / scaleFactor

            val col = floor(canvasX / cellWidth).toInt()
            val row = floor(canvasY / cellHeight).toInt()

            if (row in 0 until gridSize && col in 0 until gridSize) {
                val activeLayer = layerManager.getActiveLayer()
                layerManager.saveStateForActiveLayer()
                activeLayer.pixels[row][col] = currentColor
                invalidate()
            }
        }

        return true
    }

    // Public API

    fun undo() {
        layerManager.undoActiveLayer()
        invalidate()
    }

    fun redo() {
        layerManager.redoActiveLayer()
        invalidate()
    }

    fun clearActiveLayer() {
        layerManager.clearActiveLayer()
        invalidate()
    }

    fun setColor(color: Int) {
        currentColor = color
    }

    fun addLayer() {
        layerManager.addLayer()
        invalidate()
    }

    fun deleteLayer(index: Int) {
        layerManager.deleteLayer(index)
        invalidate()
    }

    fun duplicateLayer(index: Int) {
        layerManager.duplicateLayer(index)
        invalidate()
    }

    fun moveLayer(from: Int, to: Int) {
        layerManager.moveLayer(from, to)
        invalidate()
    }

    fun toggleLayerVisibility(index: Int) {
        layerManager.toggleVisibility(index)
        invalidate()
    }

    fun setLayerOpacity(index: Int, opacity: Float) {
        layerManager.setOpacity(index, opacity)
        invalidate()
    }

    fun setActiveLayer(index: Int) {
        layerManager.setActiveLayer(index)
        invalidate()
    }

    fun getLayers(): List<PixelLayer> = layerManager.getLayers()

    fun getActiveLayerIndex(): Int = layerManager.activeLayerIndex

    // Gesture listeners

    private inner class ScaleListener :
        ScaleGestureDetector.SimpleOnScaleGestureListener() {

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val prevScale = scaleFactor
            scaleFactor *= detector.scaleFactor
            scaleFactor = scaleFactor.coerceIn(0.5f, 20f)

            val focusX = detector.focusX
            val focusY = detector.focusY

            offsetX = focusX - (focusX - offsetX) * (scaleFactor / prevScale)
            offsetY = focusY - (focusY - offsetY) * (scaleFactor / prevScale)

            invalidate()
            return true
        }
    }

    private inner class PanListener :
        GestureDetector.SimpleOnGestureListener() {

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            offsetX -= distanceX
            offsetY -= distanceY
            invalidate()
            return true
        }
    }
}
