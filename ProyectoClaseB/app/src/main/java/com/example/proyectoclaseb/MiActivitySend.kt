package com.example.proyectoclaseb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.widget.TextView

class MiActivitySend : AppCompatActivity() {
    lateinit var lbl : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mi_send)

        val texto = intent.getStringExtra(Intent.EXTRA_TEXT)
        lbl = findViewById(R.id.txtTexto)
        lbl.text = texto
    }
}