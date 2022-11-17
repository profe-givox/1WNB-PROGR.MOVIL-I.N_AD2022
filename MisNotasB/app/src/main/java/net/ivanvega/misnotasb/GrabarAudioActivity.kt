package net.ivanvega.misnotasb

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import net.ivanvega.misnotasb.databinding.ActivityGrabarAudioBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class GrabarAudioActivity : AppCompatActivity() {

    private var mStartPlaying: Boolean = true
    private val REQUEST_CODE_PERMISOS_AUDIO: Int = 1001
    lateinit var binding : ActivityGrabarAudioBinding

    private var fileName: String = ""

    private var recorder: MediaRecorder? = null

    private var player: MediaPlayer? = null

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private var permissions: Array<String> = arrayOf("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO")

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGrabarAudioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGraabr. text = "Start recording"

        binding.btnGraabr.setOnClickListener {
            grabarAudio()
            onRecord(mStartRecording)
            binding.btnGraabr. text = when (mStartRecording) {
                true -> "Stop recording"
                false -> "Start recording"
            }
            mStartRecording = !mStartRecording

        }


        binding.btnReproducir.setOnClickListener {
            onPlay(mStartPlaying)
            (it as Button).text = when (mStartPlaying) {
                true -> "Stop playing"
                false -> "Start playing"
            }
            mStartPlaying = !mStartPlaying
        }

        binding.btnAlarma.setOnClickListener {



            alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmIntent = Intent(applicationContext, MiReceiverParaAlarma::class.java).let { intent ->
                PendingIntent.getBroadcast(applicationContext, 1001, intent, 0)
            }

            alarmMgr?.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 10 * 1000,
                alarmIntent
            )

        }

    }

    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
        player?.release()
        player = null

    }

    private fun onPlay(start: Boolean) = if (start) {
        startPlaying()
    } else {
        stopPlaying()
    }

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    private fun grabarAudio() {
        validarPermisos()
    }

    var mStartRecording = true
    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
    } else {
        stopRecording()
    }
    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }
    private val LOG_TAG = "AudioRecordTest"
    @Throws(IOException::class)
    fun createAudioFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
        return File.createTempFile(
            "AUDIO_${timeStamp}_", /* prefix */
            ".mp3", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            fileName = absolutePath
        }
    }
    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            createAudioFile()
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    private fun validarPermisos() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                "android.permission.WRITE_EXTERNAL_STORAGE"
            ) == PackageManager.PERMISSION_GRANTED &&

                    ContextCompat.checkSelfPermission(
                this,
                "android.permission.RECORD_AUDIO"
            ) == PackageManager.PERMISSION_GRANTED -> {
                // You can use the API that requires the permission.

                iniciarGrabacion()
            }
            shouldShowRequestPermissionRationale("android.permission.RECORD_AUDIO") -> {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            val dialofo = AlertDialog.Builder(this).apply {
                setTitle("Acepta permisos porfavor")
                setMessage("Debes de aceptar el persmiso de grabar audio para tener notas de audio")
                    .setNegativeButton("Ok", DialogInterface.OnClickListener {
                            dialogInterface, i ->

                    })
                    .setPositiveButton("Solicitar permiso de nuevo",
                        DialogInterface.OnClickListener { dialogInterface, i ->
                            requestPermissions(
                                arrayOf("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"),
                                REQUEST_CODE_PERMISOS_AUDIO)
                        })
                create()
            }
                dialofo.show()

        }
            else -> {
                // You can directly ask for the permission.
                requestPermissions(
                    arrayOf("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"),
                    REQUEST_CODE_PERMISOS_AUDIO)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_PERMISOS_AUDIO -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    iniciarGrabacion()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.

                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun iniciarGrabacion() {

    }
}