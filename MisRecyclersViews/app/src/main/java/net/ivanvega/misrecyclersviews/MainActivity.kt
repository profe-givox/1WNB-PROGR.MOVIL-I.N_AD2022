package net.ivanvega.misrecyclersviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.ivanvega.misrecyclersviews.data.Flower
import net.ivanvega.misrecyclersviews.data.listFlowers
import net.ivanvega.misrecyclersviews.fragments.FragmentFlowerDetail
import net.ivanvega.misrecyclersviews.fragments.FragmentListFlower

class MainActivity : AppCompatActivity() {
    lateinit var rvf : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       if( findViewById<View>(R.id.fragment_conteiner_view)!=null  &&
               supportFragmentManager.findFragmentById(R.id.fragment_conteiner_view) == null
               ){
           val managerFrag = supportFragmentManager
           val transFrag = managerFrag.beginTransaction()
           val fragmento = FragmentListFlower()
           transFrag.add(R.id.fragment_conteiner_view, fragmento )
           transFrag.commit()
       }



//        rvf = findViewById(R.id.recyclerViewFlower)
//
//        val flowerList =
//            DataSource(application).getFlowerList()
//
//        val adaptador = FlowerAdapter(listFlowers(resources)) {
//            Toast.makeText(
//                application,
//                "Elemento seleccinado: ${it.name}", Toast.LENGTH_SHORT
//            ).show()
//        }
//
//
//
//
//        rvf.layoutManager =
//            LinearLayoutManager(application,LinearLayoutManager.VERTICAL,false)
//
////        rvf.layoutManager =
////            GridLayoutManager(applicationContext,2)
//
//        rvf.adapter = adaptador
    }

    fun mostrarFlor(it: Flower) {

        val pantBig = supportFragmentManager.findFragmentById(R.id.fragDetailFL)
        if(pantBig==null) {
            val frag = FragmentFlowerDetail.newInstance(it.id.toString(), "")
            val transac = supportFragmentManager.beginTransaction()
            transac.replace(R.id.fragment_conteiner_view, frag)
            transac.addToBackStack(null)
            transac.commit()
        }else{
           pantBig?.let {
               (it as FragmentFlowerDetail).cargarDetailFlower(it.id)
           }

        }

    }
}