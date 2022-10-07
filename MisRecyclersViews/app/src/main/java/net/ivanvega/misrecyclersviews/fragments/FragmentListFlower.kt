package net.ivanvega.misrecyclersviews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.ivanvega.misrecyclersviews.FlowerAdapter
import net.ivanvega.misrecyclersviews.MainActivity
import net.ivanvega.misrecyclersviews.R
import net.ivanvega.misrecyclersviews.data.DataSource
import net.ivanvega.misrecyclersviews.data.listFlowers

class FragmentListFlower : Fragment() {

//     var rvFlores: RecyclerView? = null
       lateinit var rvFlores: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout = inflater.inflate(R.layout.layout_fragment_listflower, container, false)

        rvFlores =   layout.findViewById<RecyclerView>(R.id.rvFlowers)

        val adaptador = FlowerAdapter( DataSource.lsFlower) {
            Toast.makeText(
                activity,
                "Elemento seleccinado: ${it.name}", Toast.LENGTH_SHORT
            ).show()

            val actiPrin = activity as MainActivity

            actiPrin.mostrarFlor(it)

        }

            rvFlores.layoutManager =
        LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)

        rvFlores.adapter = adaptador

        return  layout
    }

    fun actualizar() {
        rvFlores.adapter?.notifyDataSetChanged()
    }

}