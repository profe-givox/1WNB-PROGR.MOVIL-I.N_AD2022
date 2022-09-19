package net.ivanvega.probandointentimplicito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var btnGetCon : Button
    lateinit var activityResultLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        activityResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.GetContent(),
                ActivityResultCallback {
                    Toast.makeText(
                        applicationContext, it.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            )

        btn = findViewById(R.id.btnAct)
        btnGetCon = findViewById(R.id.btnGetConrtent)
        btn.setOnClickListener {
            startActivity(
                Intent().apply {
                    action = "com.example.proyectoclaseb.ACTIVITY_SEND_PERRO"
                    putExtra(Intent.EXTRA_TEXT, "Mensaje a enviar")
                    type = "text/plain"
                }
            )
        }
        btnGetCon.setOnClickListener {
            activityResultLauncher.launch("audio/*")
        }



    }
}