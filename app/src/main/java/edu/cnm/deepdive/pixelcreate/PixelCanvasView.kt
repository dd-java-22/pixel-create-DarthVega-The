package edu.cnm.deepdive.pixelcreate

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import com.google.gson.Gson
import kotlin.math.floor
import kotlin.math.min

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

    // Tools
    enum class Tool { PENCIL, ERASER, FILL_BUCKET, EYEDROPPER }
    var currentTool: Tool = Tool.PENCIL

    // Zoom & pan
    private var scaleFactor = 1f
    private var offsetX = 0f
    private var offsetY = 0f

    private val scaleDetector = ScaleGestureDetector(context, ScaleListener())
    private val panDetector = GestureDetector(context, PanListener())

    // Track if we saved state for this stroke
    private var savedStateForStroke = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()

        canvas.translate(offsetX, offsetY)
        canvas.scale(scaleFactor, scaleFactor)

        val cellSize = min(width, height) / gridSize.toFloat()
        val cellWidth = cellSize
        val cellHeight = cellSize

        val offsetXCenter = (width - gridSize * cellSize) / 2
        val offsetYCenter = (height - gridSize * cellSize) / 2

        canvas.translate(offsetXCenter, offsetYCenter)


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

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                savedStateForStroke = false
                handleToolAction(event)
            }
            MotionEvent.ACTION_MOVE -> {
                handleToolAction(event)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                savedStateForStroke = false
            }
        }

        return true
    }

    private fun handleToolAction(event: MotionEvent) {
        val cellSize = min(width, height) / gridSize.toFloat()

        val offsetXCenter = (width - gridSize * cellSize) / 2
        val offsetYCenter = (height - gridSize * cellSize) / 2

// Reverse the transforms in the correct order
        val canvasX = (event.x - offsetX) / scaleFactor - offsetXCenter
        val canvasY = (event.y - offsetY) / scaleFactor - offsetYCenter

        val col = floor(canvasX / cellSize).toInt()
        val row = floor(canvasY / cellSize).toInt()


        if (row in 0 until gridSize && col in 0 until gridSize) {
            when (currentTool) {
                Tool.PENCIL -> drawPixel(row, col)
                Tool.ERASER -> erasePixel(row, col)
                Tool.FILL_BUCKET -> fillBucket(row, col)
                Tool.EYEDROPPER -> pickColor(row, col)
            }
        }
    }

    private fun drawPixel(row: Int, col: Int) {
        if (!savedStateForStroke) {
            layerManager.saveStateForActiveLayer()
            savedStateForStroke = true
        }
        val layer = layerManager.getActiveLayer()
        layer.pixels[row][col] = currentColor
        invalidate()
    }

    private fun erasePixel(row: Int, col: Int) {
        if (!savedStateForStroke) {
            layerManager.saveStateForActiveLayer()
            savedStateForStroke = true
        }
        val layer = layerManager.getActiveLayer()
        layer.pixels[row][col] = Color.TRANSPARENT
        invalidate()
    }

    private fun fillBucket(row: Int, col: Int) {
        layerManager.saveStateForActiveLayer()
        val layer = layerManager.getActiveLayer()
        val targetColor = layer.pixels[row][col]
        if (targetColor == currentColor) return

        floodFill(layer, row, col, targetColor, currentColor)
        invalidate()
    }

    private fun floodFill(layer: PixelLayer, row: Int, col: Int, targetColor: Int, fillColor: Int) {
        if (row !in 0 until gridSize || col !in 0 until gridSize) return
        if (layer.pixels[row][col] != targetColor) return

        layer.pixels[row][col] = fillColor

        floodFill(layer, row + 1, col, targetColor, fillColor)
        floodFill(layer, row - 1, col, targetColor, fillColor)
        floodFill(layer, row, col + 1, targetColor, fillColor)
        floodFill(layer, row, col - 1, targetColor, fillColor)
    }

    private fun pickColor(row: Int, col: Int) {
        val layer = layerManager.getActiveLayer()
        val pickedColor = layer.pixels[row][col]
        if (pickedColor != Color.TRANSPARENT) {
            currentColor = pickedColor
        }
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

    fun resetZoom() {
        scaleFactor = 1f
        offsetX = 0f
        offsetY = 0f
        invalidate()
    }

    fun setTool(tool: Tool) {
        currentTool = tool
    }

    fun exportToBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(gridSize, gridSize, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // Fill with transparent background
        canvas.drawColor(Color.TRANSPARENT)

        val paint = Paint().apply {
            style = Paint.Style.FILL
            isAntiAlias = false
        }

        // Draw all visible layers
        for (layer in layerManager.getLayers()) {
            if (!layer.visible) continue

            paint.alpha = (layer.opacity * 255).toInt()

            for (row in 0 until gridSize) {
                for (col in 0 until gridSize) {
                    val color = layer.pixels[row][col]
                    if (color != Color.TRANSPARENT) {
                        paint.color = color
                        canvas.drawRect(
                            col.toFloat(),
                            row.toFloat(),
                            (col + 1).toFloat(),
                            (row + 1).toFloat(),
                            paint
                        )
                    }
                }
            }
        }

        return bitmap
    }

    fun saveProjectToJson(): String {
        val layerDataList = layerManager.getLayers().map { layer ->
            LayerData(
                pixels = layer.pixels.map { it.toList() },
                visible = layer.visible,
                opacity = layer.opacity
            )
        }

        val projectData = ProjectData(
            gridSize = gridSize,
            layers = layerDataList,
            activeLayerIndex = layerManager.activeLayerIndex
        )

        return Gson().toJson(projectData)
    }

    fun loadProjectFromJson(json: String) {
        try {
            val projectData = Gson().fromJson(json, ProjectData::class.java)

            if (projectData.gridSize != gridSize) {
                throw IllegalArgumentException("Grid size mismatch")
            }

            // Clear existing layers
            while (layerManager.getLayers().size > 1) {
                layerManager.deleteLayer(0)
            }
            layerManager.clearActiveLayer()

            // Load all layers
            var firstLayer = true
            for (layerData in projectData.layers) {
                if (firstLayer) {
                    // Use existing first layer
                    val layer = layerManager.getActiveLayer()
                    layer.visible = layerData.visible
                    layer.opacity = layerData.opacity
                    for (row in 0 until gridSize) {
                        for (col in 0 until gridSize) {
                            layer.pixels[row][col] = layerData.pixels[row][col]
                        }
                    }
                    firstLayer = false
                } else {
                    // Add new layer
                    layerManager.addLayer()
                    val layer = layerManager.getActiveLayer()
                    layer.visible = layerData.visible
                    layer.opacity = layerData.opacity
                    for (row in 0 until gridSize) {
                        for (col in 0 until gridSize) {
                            layer.pixels[row][col] = layerData.pixels[row][col]
                        }
                    }
                }
            }

            // Set the active layer index
            layerManager.setActiveLayer(projectData.activeLayerIndex.coerceIn(0, layerManager.getLayers().lastIndex))

            invalidate()
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    fun saveToUri(uri: Uri) {
        try {
            val json = saveProjectToJson()
            context.contentResolver.openOutputStream(uri)?.use { stream ->
                stream.write(json.toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadFromUri(uri: Uri) {
        try {
            val json = context.contentResolver.openInputStream(uri)?.use { stream ->
                stream.bufferedReader().use { it.readText() }
            }
            if (json != null) {
                loadProjectFromJson(json)
                invalidate()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


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
