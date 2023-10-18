package com.example.freakygallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freakygallery.databinding.ImagenesBinding
import com.example.freakygallery.databinding.ItemImagenBinding

//Clase para controlar los métodos del viewHolder
class ImagenesAdapter(
    private val imagenes: List<Imagen>,
    private val listener: ImagenPulsadaListener
) : RecyclerView.Adapter<ImagenesAdapter.ViewHolder>() {

    //Clase viewHolder
    class ViewHolder(private val binding: ItemImagenBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //Cambia las imágenes del recycleView por las de las URL
        fun setImage(imagen: Imagen) {

            Glide
                .with(binding.root.context)
                .load(imagen.src)
                .into(binding.imagenVista)
        }

    }

    //Método para iniciar el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImagenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    //Método para conseguir el tamaño de la lista
    override fun getItemCount(): Int = imagenes.size

    //Método para colocar las imágenes
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setImage(imagenes[position])
        holder.itemView.setOnClickListener {
            listener.imagenPulsada(imagenes[position])
        }
    }
}