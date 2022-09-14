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
    lateinit var btn3SEND: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intents_action)

        btn1dial = findViewById(R.id.btnIntent1)
        btn2viewmap = findViewById(R.id.btnIntent2)
        btn3SEND = findViewById(R.id.btnIntentSend)

        btn1dial.setOnClickListener {
            val intent_dial = Intent(ACTION_DIAL, Uri.parse("tel:4451024589"))
            startActivity(intent_dial)
        }

        btn2viewmap.setOnClickListener {
            val intent_view = Intent(
                Intent.ACTION_VIEW,
                //Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California")
                Uri.parse("https://sicenet.itsur.edu.mx")
            )
            startActivity(intent_view)
        }

        btn3SEND.setOnClickListener {
            val intent_send = Intent(Intent.ACTION_SEND).apply {
                this.putExtra(Intent.EXTRA_EMAIL, "GI.VEGA@ITSUR.EDU.MX")
                putExtra(Intent.EXTRA_SUBJECT, "Este es el asunto")
                putExtra(Intent.EXTRA_TEXT, "Este es el cuerpo de un mensaje")
                type = "text/plain"
            }
            startActivity(intent_send)
        }

    }
}