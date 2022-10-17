package net.ivanvega.misnotasb.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.ivanvega.misnotasb.data.dao.NotaDao
import net.ivanvega.misnotasb.data.model.Nota
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Nota::class], version = 1)
abstract class MisNotasDatabase: RoomDatabase() {
    abstract fun notaDao(): NotaDao
    //abstract fun multimediaDao(): MultimediaDao

    companion object{
        private var INSTANCE: MisNotasDatabase? = null
        val databaseWriteExecutor : ExecutorService
            = Executors.newFixedThreadPool(4)
        fun getDataBase(context : Context) : MisNotasDatabase{
            return  INSTANCE ?: synchronized(this){
                val instance  =
                    Room.databaseBuilder(context,
                        MisNotasDatabase::class.java,
                            "midatabase"
                        ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}