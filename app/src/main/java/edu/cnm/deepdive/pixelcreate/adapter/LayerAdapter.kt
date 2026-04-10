package edu.cnm.deepdive.pixelcreate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.cnm.deepdive.pixelcreate.PixelCanvasView
import edu.cnm.deepdive.pixelcreate.R

class LayerAdapter(
    private val canvasView: PixelCanvasView
) : RecyclerView.Adapter<LayerAdapter.LayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layer, parent, false)
        return LayerViewHolder(view)
    }

    override fun getItemCount(): Int = canvasView.getLayers().size

    override fun onBindViewHolder(holder: LayerViewHolder, position: Int) {
        val layer = canvasView.getLayers()[position]
        val isActive = canvasView.getActiveLayerIndex() == position

        // Layer name
        holder.layerName.text = "Layer ${position + 1}"

        // Highlight active layer
        holder.itemView.setBackgroundColor(
            if (isActive) 0xFF555555.toInt() else 0xFF333333.toInt()
        )

        // Visibility icon
        holder.visibilityButton.setImageResource(
            if (layer.visible) R.drawable.ic_eye else R.drawable.ic_eye_off
        )

        // Select layer
        holder.itemView.setOnClickListener {
            canvasView.setActiveLayer(position)
            notifyDataSetChanged()
        }

        // Toggle visibility
        holder.visibilityButton.setOnClickListener {
            canvasView.toggleLayerVisibility(position)
            notifyItemChanged(position)
        }

        // Duplicate layer
        holder.duplicateButton.setOnClickListener {
            canvasView.duplicateLayer(position)
            notifyDataSetChanged()
        }

        // Delete layer
        holder.deleteButton.setOnClickListener {
            canvasView.deleteLayer(position)
            notifyDataSetChanged()
        }

        // Move layer up
        holder.moveUpButton.setOnClickListener {
            if (position > 0) {
                canvasView.moveLayer(position, position - 1)
                notifyDataSetChanged()
            }
        }

        // Move layer down
        holder.moveDownButton.setOnClickListener {
            if (position < canvasView.getLayers().size - 1) {
                canvasView.moveLayer(position, position + 1)
                notifyDataSetChanged()
            }
        }
    }

    inner class LayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val visibilityButton: ImageButton = view.findViewById(R.id.visibilityButton)
        val layerName: TextView = view.findViewById(R.id.layerName)
        val duplicateButton: ImageButton = view.findViewById(R.id.duplicateButton)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)
        val moveUpButton: ImageButton = view.findViewById(R.id.moveUpButton)
        val moveDownButton: ImageButton = view.findViewById(R.id.moveDownButton)
    }
}
