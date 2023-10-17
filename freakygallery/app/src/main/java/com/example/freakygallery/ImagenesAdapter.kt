package com.example.freakygallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.freakygallery.databinding.ItemImagenBinding

class ImagenesAdapter(
    private val imagenes: List<Imagen>
) : RecyclerView.Adapter<ImagenesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemImagenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setImage(imagen: Imagen) {

            Glide
                .with(binding.root.context)
                .load(imagen.src)
                .into(binding.imagenVista)
        }

        var isDataVisible = false
        binding.iconoContacto.setOnClickListener {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImagenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = imagenes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setImage(imagenes[position])
        holder.itemView.setOnClickListener {
            ImagenPulsadaListener.imagenPulsada(imagenes[position])
        }
    }
}