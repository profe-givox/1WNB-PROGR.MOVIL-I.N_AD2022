package net.ivanvega.misrecyclersviews

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.ivanvega.misrecyclersviews.data.DataSource
import net.ivanvega.misrecyclersviews.data.Flower
import net.ivanvega.misrecyclersviews.data.listFlowers
import net.ivanvega.misrecyclersviews.fragments.FragmentFlowerDetail
import net.ivanvega.misrecyclersviews.fragments.FragmentListFlower

class MainActivity : AppCompatActivity() {
    lateinit var rvf : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fcvl = findViewById<View>(R.id.fragment_conteiner_view)
        val fcvf = supportFragmentManager.findFragmentById(R.id.fragment_conteiner_view)

        if (DataSource.lsFlower.size==0){
            DataSource.lsFlower.addAll(listFlowers(resources))
        }

        if(fcvl!=null && fcvf==null) {

            val managerFrag = supportFragmentManager
            val transFrag = managerFrag.beginTransaction()
            val fragmento = FragmentListFlower()
            transFrag.add(R.id.fragment_conteiner_view, fragmento)
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
        val fcvl = findViewById<View>(R.id.fragment_conteiner_view)
        val fcvf = supportFragmentManager.findFragmentById(R.id.fragment_conteiner_view)
        //val pantBig = supportFragmentManager.findFragmentById(R.id.fragDetailFL) as FragmentFlowerDetail
        if(fcvl!=null && fcvf!=null) {
            val frag = FragmentFlowerDetail.newInstance(it.id.toString(), "")
            val transac = supportFragmentManager.beginTransaction()
            transac.replace(R.id.fragment_conteiner_view, frag)
            transac.addToBackStack(null)
            transac.commit()
        }else{
           //pantBig.cargarDetailFlower(it.id)
            val detail = supportFragmentManager.findFragmentById(R.id.fragDetailFL) as FragmentFlowerDetail
            detail.cargarDetailFlower(it.id)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteFlower(id: Any) {
        DataSource.lsFlower.removeIf { it.id == id.toString().toLong() }
        supportFragmentManager.popBackStack()
    }
}