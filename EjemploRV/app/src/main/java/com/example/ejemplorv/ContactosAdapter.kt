package com.example.ejemplorv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplorv.databinding.ItemContactoBinding

class ContactosAdapter(
    private val contactos: List<Contacto>,
    private val contactoPulsadoListener: ContactoPulsadoListener
) : RecyclerView.Adapter<ContactosAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemContactoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contacto: Contacto) {
            binding.nombre.text = contacto.nombre
            binding.tfno.text = contacto.tfno
            binding.iconoContacto.setImageResource(if (contacto.isMale) R.drawable.mancontact else R.drawable.womancontact)

            var isDataVisible = false
            var inicial=""
            var palabras = binding.nombre.text.split(" ")
            for (i in 0 until palabras.size) {
                inicial += palabras[i][0]
            }
            binding.iniciales.text=inicial

            binding.iconoContacto.setOnClickListener {
                isDataVisible = !isDataVisible
                if (isDataVisible) {
                    binding.iniciales.visibility = View.GONE
                    binding.datosContacto.visibility = View.VISIBLE
                } else {
                    binding.iniciales.visibility = View.VISIBLE
                    binding.datosContacto.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = contactos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contactos[position])
        holder.itemView.setOnClickListener {
            contactoPulsadoListener.contactoPulsado(contactos[position])
        }
    }
}