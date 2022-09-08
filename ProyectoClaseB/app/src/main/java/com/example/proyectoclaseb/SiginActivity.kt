package com.example.proyectoclaseb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SiginActivity : AppCompatActivity() {
    lateinit var txtN : EditText
    lateinit var txtE : EditText
    lateinit var btnSave : Button

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

        txtN = findViewById(R.id.txtName)
        txtE = findViewById(R.id.txtEmail)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener{
            val inntentresult = Intent()
            inntentresult.putExtra("email", txtE.text.toString())
            setResult(RESULT_OK, inntentresult )
            finish()
        }




    }
}