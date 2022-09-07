package com.example.proyectoclaseb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class SiginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin)

//        Recuperar parametros del  intent
        val titulo = intent.getStringExtra("otropar")
        title = titulo   //asigna un titulo a la actividad
//        this.title = titulo

        val accion = intent.getIntExtra("param1", -1)
        Toast.makeText(applicationContext, "Accion: $accion",
            Toast.LENGTH_LONG).show()

    }
}