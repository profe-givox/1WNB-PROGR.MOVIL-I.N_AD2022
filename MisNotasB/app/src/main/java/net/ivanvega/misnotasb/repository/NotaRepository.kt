package net.ivanvega.misnotasb.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import net.ivanvega.misnotasb.data.dao.NotaDao
import net.ivanvega.misnotasb.data.model.Nota

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NotaRepository (private val daoLocal: NotaDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allNotas: Flow<List<Nota>> = daoLocal.getAllOrder()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertarAsync(nota: Nota) {
       daoLocal.insertar(nota)
    }

}