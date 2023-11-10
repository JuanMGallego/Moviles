package com.example.ejemplorv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplorv.databinding.ItemContactoBinding

class ContactosAdapter(
    val tasks: List< TaskEntity>,                   // Objeto Lista de tareas
    val checkTask: (TaskEntity) -> Unit,            // chequeo de tarea
    val deleteTask: (TaskEntity) -> Unit,
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
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return tasks.size     // Devuelve el número de tareas de la lista
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = tasks[position]
        holder.bind(item, checkTask, deleteTask)
        holder.itemView.setOnClickListener {
            contactoPulsadoListener.contactoPulsado(contactos[position])
        }
    }
}