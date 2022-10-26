package net.ivanvega.misnotasb

import android.app.Application
import net.ivanvega.misnotasb.data.database.MisNotasDatabase
import net.ivanvega.misnotasb.data.model.Nota
import net.ivanvega.misnotasb.repository.NotaRepository

class MisNotasApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MisNotasDatabase.getDataBase(this) }
    val repository by lazy { NotaRepository(database.notaDao()) }
}