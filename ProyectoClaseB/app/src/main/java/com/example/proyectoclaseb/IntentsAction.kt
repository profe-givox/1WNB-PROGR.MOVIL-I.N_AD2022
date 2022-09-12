package com.example.proyectoclaseb

import android.content.Intent
import android.content.Intent.ACTION_DIAL
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IntentsAction : AppCompatActivity() {
    lateinit var btn1dial: Button
    lateinit var btn2viewmap: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents_action)

        btn1dial = findViewById(R.id.btnIntent1)
        btn2viewmap = findViewById(R.id.btnIntent2)

        btn1dial.setOnClickListener {
            val intent_dial = Intent(ACTION_DIAL, Uri.parse("tel:4451024589"))
            startActivity(intent_dial)
        }
    }
}