package net.ivanvega.misrecyclersviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.ivanvega.misrecyclersviews.data.Flower
import net.ivanvega.misrecyclersviews.data.listFlowers

class MainActivity : AppCompatActivity() {
    lateinit var rvf : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvf = findViewById(R.id.recyclerViewFlower)

        val flowerList =
            DataSource(application).getFlowerList()

        val adaptador = FlowerAdapter(listFlowers(resources)) {
            Toast.makeText(
                application,
                "Elemento seleccinado: ${it.name}", Toast.LENGTH_SHORT
            ).show()
        }




        rvf.layoutManager =
            LinearLayoutManager(application,LinearLayoutManager.VERTICAL,false)

//        rvf.layoutManager =
//            GridLayoutManager(applicationContext,2)

        rvf.adapter = adaptador
    }
}