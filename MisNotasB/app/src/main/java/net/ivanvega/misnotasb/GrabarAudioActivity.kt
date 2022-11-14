package net.ivanvega.misnotasb

import android.content.DialogInterface
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import net.ivanvega.misnotasb.databinding.ActivityGrabarAudioBinding

class GrabarAudioActivity : AppCompatActivity() {

    private val REQUEST_CODE_PERMISOS_AUDIO: Int = 1001
    lateinit var binding : ActivityGrabarAudioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGrabarAudioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGraabr.setOnClickListener { grabarAudio() }
    }

    private fun grabarAudio() {
        validarPermisos()
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
        TODO("Not yet implemented")
    }
}