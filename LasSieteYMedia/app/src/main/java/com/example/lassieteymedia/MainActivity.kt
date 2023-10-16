package com.example.lassieteymedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lassieteymedia.databinding.JuegoBinding
import com.example.lassieteymedia.databinding.LoginBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingLogin: LoginBinding
    private lateinit var bindingJuego: JuegoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingLogin = LoginBinding.inflate(layoutInflater)
        bindingJuego = JuegoBinding.inflate(layoutInflater)

        setContentView(bindingLogin.root)

        bindingLogin.enviar.setOnClickListener {
            val nombreJ1 = bindingLogin.edtNombreJ1.text.toString()
            val nombreJ2 = bindingLogin.edtNombreJ2.text.toString()

            if ((nombreJ1.isNotBlank() && nombreJ2.isNotBlank()) && (nombreJ1.length <= 9 && nombreJ2.length <= 9)) {
                setContentView(bindingJuego.root)

                bindingJuego.nombreJ1.text = nombreJ1
                bindingJuego.nombreJ2.text = nombreJ2

            } else {
                showToast("Los nombres no pueden estar vacíos y max. 9 caracteres")
            }

        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}