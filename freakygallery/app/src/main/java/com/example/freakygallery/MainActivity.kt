package com.example.freakygallery

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.freakygallery.databinding.ImagenesBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imagenes = ImagenesBinding.inflate(layoutInflater)
        setContentView(imagenes.root)

        imagenes.vistaImagenes.adapter = ImagenesAdapter(listOf(
            Imagen("https://loremflickr.com/320/240/cat?lock=1")
        ), object : ContactoPulsadoListener{

            override fun contactoPulsado(contacto: Contacto) {
                val dial = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:" + contacto.tfno)
                )
                startActivity(dial)
            }

        })

    }
}