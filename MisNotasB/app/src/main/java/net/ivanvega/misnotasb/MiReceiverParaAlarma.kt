package net.ivanvega.misnotasb

import android.R
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.asLiveData


class MiReceiverParaAlarma : BroadcastReceiver () {
    override fun onReceive(p0: Context?, p1: Intent?) {
        p0?.let { createNotificationChannel(it, p1) }
        if (p1?.action == "android.intent.action.BOOT_COMPLETED") {
            // Set the alarm here.
            Toast.makeText(p0, "SE reinicio el cel", Toast.LENGTH_LONG).show()
            //Reprogramar una alarma

            val rep = (p0?.applicationContext as MisNotasApplication).repository

            val lnotasLiveData = rep.allNotas.asLiveData()

            lnotasLiveData.observeForever {
                for  ( nota in it ){
                    Log.d("NOTRAS", nota.toString())
                }
            }
             val alarmMgr = p0?. getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val  alarmIntent = Intent(p0.applicationContext, MiReceiverParaAlarma::class.java).let { intent ->
                PendingIntent.getBroadcast(p0.applicationContext, 1002, intent, 0)
            }

            alarmMgr?.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 10 * 1000,
                alarmIntent
            )
        }else{
            Log.d("ALRMA", "LA ALARMA SE ACTIVO")
            p0?.let { p1?.let { it1 -> mostrarNotificacion(it, it1) } }
        }




    }
    private fun mostrarNotificacion(context: Context, intent: Intent) {
        //createNotificationChannel(context,intent);

        // Create an explicit intent for an Activity in your app
        //PendingIntent.FLAG_UPDATE_CURRENT
        val intentTap = Intent(context, MainActivity::class.java)
        intentTap.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        intentTap.putExtra("idTarea", 1001)
        val pendingIntent = PendingIntent.getActivity(
            context, 0,
            intentTap, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_btn_speak_now)
            .setContentTitle("Titulo recordatorio")
            .setContentText("Te recuerdo tarea pendiente")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        val notificationManager = NotificationManagerCompat.from(context)

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1001, builder.build())
    }
    private val CHANNEL_ID = "RECORDATORIOS-TAREAS"
    fun createNotificationChannel(ctx: Context, intent: Intent?) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = ctx.getString(net.ivanvega.misnotasb.R.string.channel_name)
            val description = ctx.getString(net.ivanvega.misnotasb.R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = ctx.getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }
    }
}