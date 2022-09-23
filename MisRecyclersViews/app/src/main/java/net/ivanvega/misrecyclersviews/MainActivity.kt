package net.ivanvega.misrecyclersviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rvf : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvf = findViewById(R.id.recyclerViewFlower)

        val flowerList =
            DataSource(application).getFlowerList()

        val adaptador = FlowerAdapter(flowerList)

        //rvf.layoutManager =
 //           LinearLayoutManager(application,LinearLayoutManager.VERTICAL,false)

        rvf.layoutManager =
            GridLayoutManager(applicationContext,2)

        rvf.adapter = adaptador
    }
}