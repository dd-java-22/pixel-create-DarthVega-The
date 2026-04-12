package edu.cnm.deepdive.pixelcreate

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream

/**
 * Handles saving and loading PixelCreate project files (.pcp).
 *
 * Format:
 *  Magic header: "PCP1"
 *  int gridSize
 *  int layerCount
 *  int activeLayerIndex
 *
 *  For each layer:
 *      boolean visible
 *      float opacity
 *      int[gridSize][gridSize] pixel colors (ARGB)
 */
object ProjectSerializer {

    private const val MAGIC = "PCP1"

    fun saveProject(manager: LayerManager, output: OutputStream) {
        val data = DataOutputStream(output)

        // Magic header
        data.writeBytes(MAGIC)

        val gridSize = manager.gridSize
        val layers = manager.getLayers()

        data.writeInt(gridSize)
        data.writeInt(layers.size)
        data.writeInt(manager.activeLayerIndex)

        for (layer in layers) {
            data.writeBoolean(layer.visible)
            data.writeFloat(layer.opacity)

            for (r in 0 until gridSize) {
                for (c in 0 until gridSize) {
                    data.writeInt(layer.pixels[r][c])
                }
            }
        }

        data.flush()
    }

    fun loadProject(input: InputStream): LayerManager {
        val data = DataInputStream(input)

        // Validate header
        val headerBytes = ByteArray(4)
        data.readFully(headerBytes)
        val header = String(headerBytes)
        if (header != MAGIC) {
            throw IllegalArgumentException("Invalid project file format")
        }

        val gridSize = data.readInt()
        val layerCount = data.readInt()
        val activeIndex = data.readInt()

        val manager = LayerManager(gridSize)
        manager.clearAllLayers()

        repeat(layerCount) {
            val layer = PixelLayer(gridSize)
            layer.visible = data.readBoolean()
            layer.opacity = data.readFloat()

            for (r in 0 until gridSize) {
                for (c in 0 until gridSize) {
                    layer.pixels[r][c] = data.readInt()
                }
            }

            manager.addExistingLayer(layer)
        }

        manager.setActiveLayer(activeIndex)
        return manager
    }
}
