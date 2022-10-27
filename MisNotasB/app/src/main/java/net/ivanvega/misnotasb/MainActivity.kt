package net.ivanvega.misnotasb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.ivanvega.misnotasb.data.database.MisNotasDatabase
import net.ivanvega.misnotasb.data.model.Nota
import net.ivanvega.misnotasb.databinding.ActivityMainBinding
import net.ivanvega.misnotasb.repository.NotaViewModel
import net.ivanvega.misnotasb.repository.NotaViewModelFactory
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private var contador: Int = 0
    lateinit var db : MisNotasDatabase
    lateinit var binding: ActivityMainBinding

    private val notaViewModel: NotaViewModel by viewModels {
        NotaViewModelFactory((application as MisNotasApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //db = MisNotasDatabase.getDataBase(applicationContext, )

        val recyclerView = binding.recyclerview
        val adapter = NotaListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        notaViewModel.allNotas.observe(this){   notas ->
            notas?.let {
                adapter.submitList(it)
                /*for( nota in  it){
                    Log.d("OBSERVADO", "$nota")
                }*/
            }
        }

        binding.btnI.setOnClickListener {
            Log.i("NOTAX", "paso insert")

            notaViewModel.insertAsync(Nota(0, "Nueva nota ${++contador}","Descr", null))
//             val daoN =  db.notaDao()
//            MisNotasDatabase.databaseWriteExecutor.execute{
//                daoN.insertar(Nota(
//                    0,"Titulo nota",
//                    "Descripción de nota",
//                    "01/01/2022"
//                ))
//            }


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