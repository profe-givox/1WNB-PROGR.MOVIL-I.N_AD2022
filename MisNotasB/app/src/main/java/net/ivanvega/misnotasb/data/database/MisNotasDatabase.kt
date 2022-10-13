package net.ivanvega.misnotasb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import net.ivanvega.misnotasb.data.dao.NotaDao
import net.ivanvega.misnotasb.data.model.Nota

@Database(entities = [Nota::class], version = 1)
abstract class MisNotasDatabase: RoomDatabase() {
    abstract fun notaDao(): NotaDao
    //abstract fun multimediaDao(): MultimediaDao
}