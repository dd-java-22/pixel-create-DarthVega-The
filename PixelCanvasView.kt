class PixelCanvasView(private val gridSize: Int = 32) {
    init {
        require(gridSize in listOf(16, 32, 64, 128)) { "Unsupported grid size: \$gridSize" }
    }

    // Other class members and functions would go here...
}

// Usage:
fun main() {
    val canvas1 = PixelCanvasView(16)
    val canvas2 = PixelCanvasView(32)
    val canvas3 = PixelCanvasView(64)
    val canvas4 = PixelCanvasView(128)
}