package com.example.calculadora20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.calculadora20.databinding.ActivityMainBinding
import com.example.calculadora20.databinding.BienvenidaBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bindingLogin: ActivityMainBinding
    private lateinit var bindingBienvenida: BienvenidaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingLogin = ActivityMainBinding.inflate(layoutInflater)
        bindingBienvenida = BienvenidaBinding.inflate(layoutInflater)

        setContentView(bindingLogin.root)

        bindingLogin.buttonAccess.setOnClickListener {
            val username = bindingLogin.editTextUsername.text.toString()
            val password = bindingLogin.editTextPassword.text.toString()

            if (password == "contra" && username == "Juanma") {
                setContentView(bindingBienvenida.root)
            } else {
                showToast("Usuario y/o contraseña incorrecta")
            }
        }

        bindingBienvenida.buttonVolver.setOnClickListener {
            bindingLogin.editTextUsername.text.clear()
            bindingLogin.editTextPassword.text.clear()
            setContentView(bindingLogin.root)
        }

        bindingBienvenida.buttonSumar.setOnClickListener {
            realizarOperacion(bindingBienvenida.editTextFactor1, bindingBienvenida.editTextFactor2, "+")
        }

        bindingBienvenida.buttonRestar.setOnClickListener {
            realizarOperacion(bindingBienvenida.editTextFactor1, bindingBienvenida.editTextFactor2, "-")
        }

        bindingBienvenida.buttonMultiplicar.setOnClickListener {
            realizarOperacion(bindingBienvenida.editTextFactor1, bindingBienvenida.editTextFactor2, "*")
        }

        bindingBienvenida.buttonDividir.setOnClickListener {
            realizarOperacion(bindingBienvenida.editTextFactor1, bindingBienvenida.editTextFactor2, "/")
        }
    }

    private fun realizarOperacion(input1: EditText, input2: EditText, operador: String) {
        val stringNum1 = input1.text.toString()
        val stringNum2 = input2.text.toString()
        val num1 = stringNum1.toDoubleOrNull() ?: 0.0
        val num2 = stringNum2.toDoubleOrNull() ?: 0.0

        val result = when (operador) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 != 0.0) {
                    num1 / num2
                } else {
                    showToast("División por cero no permitida")
                    return
                }
            }
            else -> {
                showToast("Operador no válido")
                return
            }
        }

        bindingBienvenida.textViewResultado.text = "$result"

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}