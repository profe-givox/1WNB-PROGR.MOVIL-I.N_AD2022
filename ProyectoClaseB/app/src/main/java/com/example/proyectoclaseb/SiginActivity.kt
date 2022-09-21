package com.example.proyectoclaseb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener

class SiginActivity : AppCompatActivity(), OnItemSelectedListener {

    private lateinit var arrSex: Array<String>
    lateinit var txtN : EditText
    lateinit var txtE : EditText
    lateinit var btnSave : Button
    lateinit var spnSex : Spinner

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin)

        spnSex = findViewById(R.id.spnSexo)

        //1 Coleccion

        arrSex =
            resources.getStringArray(R.array.arrSexo)

        // 2 Adapdator
        val adaptador = ArrayAdapter(applicationContext,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                arrSex
            )

        spnSex.adapter =adaptador

        //spnSex.onItemSelectedListener = this
        spnSex.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //TODO("Not yet implemented")
                val itemSele = arrSex[p2]
                Toast.makeText(applicationContext,
                    "Elemt select: $itemSele",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("SPINNER", "Elemt select: $itemSele")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //TODO("Not yet implemented")
            }

        }


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
            spnSex.selectedItem
            val inntentresult = Intent()
            inntentresult.putExtra("email", txtE.text.toString() + " " + spnSex.selectedItem )
            setResult(RESULT_OK, inntentresult )
            finish()
        }




    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //TODO("Not yet implemented")
        val itemSele = arrSex[p2]
        Toast.makeText(applicationContext,
            "Elemt select: $itemSele",
            Toast.LENGTH_LONG
        ).show()
        Log.d("SPINNER", "Elemt select: $itemSele")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //TODO("Not yet implemented")
    }
}