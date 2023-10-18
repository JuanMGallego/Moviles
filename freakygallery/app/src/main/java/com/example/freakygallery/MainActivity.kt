package com.example.freakygallery

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.freakygallery.databinding.ImagenZoomBinding
import com.example.freakygallery.databinding.ImagenesBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val imagenes = ImagenesBinding.inflate(layoutInflater)
        setContentView(imagenes.root)

        imagenes.vistaImagenes.adapter = ImagenesAdapter(
            listOf(
                Imagen("https://loremflickr.com/320/240/cat?lock=1"),
                Imagen("https://loremflickr.com/320/240/cat?lock=2"),
                Imagen("https://loremflickr.com/320/240/cat?lock=3"),
                Imagen("https://loremflickr.com/320/240/cat?lock=4"),
                Imagen("https://loremflickr.com/320/240/cat?lock=5"),
                Imagen("https://loremflickr.com/320/240/cat?lock=6"),
                Imagen("https://loremflickr.com/320/240/cat?lock=7"),
                Imagen("https://loremflickr.com/320/240/cat?lock=8"),
                Imagen("https://loremflickr.com/320/240/cat?lock=9"),
                Imagen("https://loremflickr.com/320/240/cat?lock=10"),
                Imagen("https://loremflickr.com/320/240/cat?lock=12"),
                Imagen("https://loremflickr.com/320/240/cat?lock=13"),
                Imagen("https://loremflickr.com/320/240/cat?lock=14"),
                Imagen("https://loremflickr.com/320/240/cat?lock=15")
            ), object : ImagenPulsadaListener{

                override fun imagenPulsada(imagen: Imagen) {

                    val binding = ImagenZoomBinding.inflate(layoutInflater)
                    setContentView(binding.root)

                    Glide
                        .with(binding.imagenZoom.context)
                        .load(imagen.src)
                        .into(binding.imagenZoom)

                    binding.imagenZoom.setOnClickListener {

                        setContentView(imagenes.root)

                    }

                }

            })

    }

}