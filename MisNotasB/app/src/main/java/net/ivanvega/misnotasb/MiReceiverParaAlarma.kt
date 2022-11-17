package net.ivanvega.misnotasb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MiReceiverParaAlarma : BroadcastReceiver () {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("ALRMA", "LA ALARMA SE ACTIVO")
    }
}