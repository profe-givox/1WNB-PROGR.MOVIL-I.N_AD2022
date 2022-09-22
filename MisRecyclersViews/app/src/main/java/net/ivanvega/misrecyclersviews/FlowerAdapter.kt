package net.ivanvega.misrecyclersviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FlowerAdapter (val datasource: Array<String>) : RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {
    class FlowerViewHolder (view: View)  : RecyclerView.ViewHolder(view) {
        val txt : TextView
        init {
            txt = view.findViewById(R.id.flower_text)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {

        val layout_item = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_flower, parent, false)

        return FlowerViewHolder(layout_item)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.txt.text = datasource[position]
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

}