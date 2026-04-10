package edu.cnm.deepdive.pixelcreate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import edu.cnm.deepdive.pixelcreate.R

class ColorSwatchAdapter(
    private val colors: IntArray,
    private val listener: ColorSelectListener
) : RecyclerView.Adapter<ColorSwatchAdapter.ColorViewHolder>() {

    private var selectedIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_color_swatch, parent, false)
        return ColorViewHolder(view)
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = colors[position]

        // Set swatch color
        holder.swatch.setBackgroundColor(color)

        // Highlight selected swatch
        holder.border.visibility =
            if (position == selectedIndex) View.VISIBLE else View.INVISIBLE

        // Handle click
        holder.itemView.setOnClickListener {
            val pos = holder.bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) {
                selectedIndex = pos
                notifyDataSetChanged()
                listener.onColorSelected(colors[pos])
            }
        }

    }

    inner class ColorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val swatch: View = view.findViewById(R.id.color_swatch)
        val border: View = view.findViewById(R.id.color_border)
    }
}
