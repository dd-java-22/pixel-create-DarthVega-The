package edu.cnm.deepdive.pixelcreate

data class ProjectData(
    val gridSize: Int,
    val layers: List<LayerData>,
    val activeLayerIndex: Int
)

data class LayerData(
    val pixels: List<List<Int>>, // 2D array as list of lists
    val visible: Boolean,
    val opacity: Float
)
