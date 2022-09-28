package net.ivanvega.misrecyclersviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.callbackFlow
import net.ivanvega.misrecyclersviews.data.Flower

class FlowerAdapter (val datasource: List<Flower>, val callBackOnClik :  (Flower) -> Unit) :
    RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {
    class FlowerViewHolder (view: View,  callBackOnClik :  (Flower) -> Unit)  : RecyclerView.ViewHolder(view) {
        val txt : TextView
        val img : ImageView
        var currrentFlower : Flower? = null
        init {
            view.setOnClickListener {
                currrentFlower?.let {
                    callBackOnClik (it)
                }
            }
            txt = view.findViewById(R.id.flower_text)
            img = view.findViewById(R.id.flower_image)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {

        val layout_item = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item_flower_custom, parent, false)

        return FlowerViewHolder(layout_item, callBackOnClik)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.currrentFlower = datasource[position]
        holder.txt.text = datasource[position].name
        datasource[position].image?.let { holder.img.setImageResource(it) }
    }

    override fun getItemCount(): Int {
        return datasource.size
    }

}