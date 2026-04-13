package edu.cnm.deepdive.pixelcreate

import android.graphics.Color

class LayerManager(var gridSize: Int) {  // <-- now var so canvas can resize

    private val layers = mutableListOf<PixelLayer>()
    var activeLayerIndex: Int = 0
        private set

    init {
        addLayer()
    }

    fun addLayer() {
        layers.add(PixelLayer(gridSize))
        activeLayerIndex = layers.lastIndex
    }

    fun addExistingLayer(layer: PixelLayer) {
        layers.add(layer)
        activeLayerIndex = layers.lastIndex
    }

    fun clearAllLayers() {
        layers.clear()
        activeLayerIndex = 0
    }

    fun deleteLayer(index: Int) {
        if (layers.size > 1 && index in layers.indices) {
            layers.removeAt(index)
            activeLayerIndex = layers.lastIndex.coerceAtLeast(0)
        }
    }

    fun duplicateLayer(index: Int) {
        if (index in layers.indices) {
            val original = layers[index]
            val copy = PixelLayer(gridSize, original.visible, original.opacity)

            for (r in 0 until gridSize) {
                for (c in 0 until gridSize) {
                    copy.pixels[r][c] = original.pixels[r][c]
                }
            }

            layers.add(index + 1, copy)
            activeLayerIndex = index + 1
        }
    }

    fun moveLayer(from: Int, to: Int) {
        if (from in layers.indices && to in layers.indices) {
            val layer = layers.removeAt(from)
            layers.add(to, layer)
            activeLayerIndex = to
        }
    }

    fun toggleVisibility(index: Int) {
        if (index in layers.indices) {
            layers[index].visible = !layers[index].visible
        }
    }

    fun setOpacity(index: Int, opacity: Float) {
        if (index in layers.indices) {
            layers[index].opacity = opacity.coerceIn(0f, 1f)
        }
    }

    fun setActiveLayer(index: Int) {
        if (index in layers.indices) {
            activeLayerIndex = index
        }
    }

    fun getLayers(): List<PixelLayer> = layers

    fun getActiveLayer(): PixelLayer = layers[activeLayerIndex]

    fun clearActiveLayer() {
        val layer = getActiveLayer()
        layer.saveState()
        for (r in 0 until gridSize) {
            for (c in 0 until gridSize) {
                layer.pixels[r][c] = Color.TRANSPARENT
            }
        }
    }

    fun saveStateForActiveLayer() {
        getActiveLayer().saveState()
    }

    fun undoActiveLayer() {
        getActiveLayer().undo()
    }

    fun redoActiveLayer() {
        getActiveLayer().redo()
    }

    fun replaceWith(other: LayerManager) {
        this.clearAllLayers()
        for (layer in other.getLayers()) {
            this.addExistingLayer(layer)
        }
        this.setActiveLayer(other.activeLayerIndex)
    }

    // ------------------------------------------------------------
    // NEW: Resize the entire canvas and all layers
    // ------------------------------------------------------------
    fun resizeCanvas(newSize: Int) {
        if (newSize == gridSize) return

        val newLayers = mutableListOf<PixelLayer>()

        for (layer in layers) {
            val resized = PixelLayer(layer, newSize)  // uses your new constructor
            newLayers.add(resized)
        }

        layers.clear()
        layers.addAll(newLayers)

        gridSize = newSize
        activeLayerIndex = activeLayerIndex.coerceIn(0, layers.lastIndex)
    }
}
