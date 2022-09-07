package com.example.proyectoclaseb

import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    lateinit var btnSignin : Button
    lateinit var actResulLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_login)

        actResulLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), ActivityResultCallback {
                
            })

        btnSignin = findViewById(R.id.btnSigin)
        btnSignin.setOnClickListener {
            //Para abrir otra actividad se debe realizar
            //lo sigueinte:
            //1.1) Declarar una varaible de tipo Intent e inicilizarla con la actividad que se abrira
//            val intent_activity_sigin = Intent(applicationContext, SiginActivity::class.java)
//            //Para pasar parametros a una actividad se
//            // realiza mediante el metodo putExtra del objeto intent
//            intent_activity_sigin.putExtra("param1", 0)
//            intent_activity_sigin.putExtra("otropar", "Register")

//            1.2
            val intent_activity_sigin =
                Intent(applicationContext, SiginActivity::class.java).apply {
                    this.putExtra("param1", 0)
                    this.putExtra("otropar", "Register")
                }

            //2) Mandar llamar la actividad con el metodo:
            startActivity(intent_activity_sigin)




        }
    }

}