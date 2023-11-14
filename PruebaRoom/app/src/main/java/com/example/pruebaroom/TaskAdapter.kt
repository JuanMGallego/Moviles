package com.example.pruebaroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.pruebaroom.databinding.ItemTaskBinding
import com.example.pruebaroom.entidades.TaskEntity

class TasksAdapter(
    val tasks: List<TaskEntity>,                   // Objeto Lista de tareas
    val checkTask: (TaskEntity) -> Unit,            // chequeo de tarea
    val deleteTask: (TaskEntity) -> Unit            // borrado de tarea
) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {    // Devuelve la vista

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {     // Muestra la vista (holder) y cada tarea de la lista (position)
        val item =
            tasks[position]                                         // Extrae la tarea de la lista
        holder.bind(
            item,
            checkTask,
            deleteTask
        )                           // Muestra el item en la vista (ver más adelante)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

    override fun getItemCount(): Int {
        return tasks.size     // Devuelve el número de tareas de la lista
    }

    class ViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {     // Clase con la vista
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
        }    // instancia del Checkbox de la vista

        fun bind(                                   // función que une los elementos en la vista y prepara los listeners
            task: TaskEntity,
            checkTask: (TaskEntity) -> Unit,
            deleteTask: (TaskEntity) -> Unit
        ) {
            binding.nombre.text = task.name
            binding.tfno.text = task.tfno
            itemView.setOnClickListener { deleteTask(task) }
        }
    }
}