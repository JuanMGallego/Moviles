package com.example.miprimeraapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.miprimeraapp.databinding.ActivityMainBinding
import com.example.miprimeraapp.databinding.BienvenidaBinding
import com.example.miprimeraapp.databinding.PauseBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingLogin: ActivityMainBinding
    private lateinit var bindingBienvenida: BienvenidaBinding
    private lateinit var bindingPause: PauseBinding

    private val channelId = "session_closed_notification"
    private val notificationId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingLogin = ActivityMainBinding.inflate(layoutInflater)
        bindingBienvenida = BienvenidaBinding.inflate(layoutInflater)
        bindingPause = PauseBinding.inflate(layoutInflater)

        setContentView(bindingLogin.root)

        bindingLogin.buttonAccess.setOnClickListener {
            val username = bindingLogin.editTextUsername.text.toString()
            val password = bindingLogin.editTextPassword.text.toString()

            if (password == "contra") {
                showWelcomeMessage(username)
            } else {
                showToast("Contraseña incorrecta")
            }
        }
    }

    private fun showWelcomeMessage(username: String) {
        setContentView(bindingBienvenida.root)
        bindingBienvenida.textView.text = "Nos alegramos de que vuelvas, $username"
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        setContentView(bindingPause.root)
    }

    override fun onRestart() {
        super.onRestart()

        val username = bindingLogin.editTextUsername.text.toString()
        val password = bindingLogin.editTextPassword.text.toString()

        if (password == "contra") {
            showWelcomeMessage(username)
            showToast("Bienvenido de nuevo, $username")
        } else {
            setContentView(bindingLogin.root)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.notificacion)
            .setContentTitle("Sesión cerrada")
            .setContentText("Se ha cerrado la sesión de " + bindingLogin.editTextUsername.text.toString() + ". Debes iniciar sesión nuevamente.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }
    }

}