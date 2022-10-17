package net.ivanvega.misnotasb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import net.ivanvega.misnotasb.data.database.MisNotasDatabase
import net.ivanvega.misnotasb.data.model.Nota
import net.ivanvega.misnotasb.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var db : MisNotasDatabase
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = MisNotasDatabase.getDataBase(applicationContext)

        binding.btnI.setOnClickListener {
            Log.i("NOTAX", "paso insert")
             val daoN =  db.notaDao()
            MisNotasDatabase.databaseWriteExecutor.execute{
                daoN.insertar(Nota(
                    0,"Titulo nota",
                    "Descripción de nota",
                    "01/01/2022"
                ))
            }


        }

        binding.btnL.setOnClickListener {
            val dao = db.notaDao()
            MisNotasDatabase.databaseWriteExecutor.execute {
                val ls = dao.getAll()
                for (item in ls){
                    Log.i("NOTAX",item.toString() )
                }
            }

        }


    }
}