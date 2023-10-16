package com.example.ejemplorv

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ejemplorv.databinding.ActivityMainBinding
import com.example.ejemplorv.databinding.ContactosBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contactos = ContactosBinding.inflate(layoutInflater)
        setContentView(contactos.root)

        contactos.vistaContactos.adapter = ContactosAdapter(listOf(
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
            Contacto("Juan  Fernandez Trujillo", "623582384", isMale = true),
            Contacto("María Sopla Mocos", "685938912", isMale = false),
            Contacto("Raúl Díaz Palo", "690123490", isMale = true),
            Contacto("Ana María Rodriguez Lopez", "602347124", isMale = false),
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