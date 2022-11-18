package net.ivanvega.misnotasb

import android.app.Application
import android.content.ComponentName
import android.content.pm.PackageManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import net.ivanvega.misnotasb.data.database.MisNotasDatabase
import net.ivanvega.misnotasb.data.model.Nota
import net.ivanvega.misnotasb.repository.NotaRepository

class MisNotasApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MisNotasDatabase.getDataBase(this, applicationScope) }
    val repository by lazy { NotaRepository(database.notaDao()) }

    override fun onCreate() {
        super.onCreate()
        val receiver = ComponentName(this, MiReceiverParaAlarma::class.java)

        this.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

}