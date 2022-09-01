package net.ivanvega.mirollerb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var etiqueta: TextView
    lateinit var boton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_roller)

        boton = findViewById(R.id.btnClick)
        etiqueta = findViewById(R.id.lblRoller)
        //forma larga de asignar listener
        /*
        boton.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext,
                "Boton presionado", Toast.LENGTH_LONG ).show()
        })*/

        boton.setOnClickListener {
            Toast.makeText(applicationContext,
                "Boton presionado", Toast.LENGTH_LONG ).show()

            val aleatorio = diceRoller()
            etiqueta.text = "Alatorio ${aleatorio}"

        }
    }

    fun diceRoller(par: Int = 0): Int{
        return (1..6).random()
    }

}