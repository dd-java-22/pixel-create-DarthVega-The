package edu.cnm.deepdive.pixelcreate

import android.graphics.Color

class PixelLayer(
    val gridSize: Int,
    initialVisible: Boolean = true,
    initialOpacity: Float = 1f
) {

    val pixels: Array<Array<Int>> =
        Array(gridSize) { Array(gridSize) { Color.TRANSPARENT } }

    var visible: Boolean = initialVisible
    var opacity: Float = initialOpacity

    private val undoStack = ArrayDeque<Array<Array<Int>>>()
    private val redoStack = ArrayDeque<Array<Array<Int>>>()

    fun saveState() {
        undoStack.addLast(copyPixels())
        redoStack.clear()
    }

    fun undo() {
        if (undoStack.isNotEmpty()) {
            redoStack.addLast(copyPixels())
            val prev = undoStack.removeLast()
            restorePixels(prev)
        }
    }

    fun redo() {
        if (redoStack.isNotEmpty()) {
            undoStack.addLast(copyPixels())
            val next = redoStack.removeLast()
            restorePixels(next)
        }
    }

    private fun copyPixels(): Array<Array<Int>> =
        Array(gridSize) { r ->
            Array(gridSize) { c -> pixels[r][c] }
        }

    private fun restorePixels(snapshot: Array<Array<Int>>) {
        for (r in 0 until gridSize) {
            for (c in 0 until gridSize) {
                pixels[r][c] = snapshot[r][c]
            }
        }
    }
}
