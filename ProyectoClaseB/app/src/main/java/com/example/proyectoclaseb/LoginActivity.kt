package com.example.proyectoclaseb

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    lateinit var btnSignin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)

        btnSignin = findViewById(R.id.btnSigin)
        btnSignin.setOnClickListener {
            //Para abrir otra actividad se debe realizar
            //lo sigueinte:
            //1) Declarar una varaible de tipo Intent e inicilizarla con la actividad que se abrira
            val intent_activity_sigin = Intent(applicationContext, SiginActivity::class.java)
            //2) Mandar llamar la actividad con el metodo:
            startActivity(intent_activity_sigin)


        }
    }

}