package com.example.freakygallery

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.freakygallery.databinding.ItemImagenBinding

class ImagenesAdapter(
    private val imagenes: List<Imagen>
) : RecyclerView.Adapter<ImagenesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemImagenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imagen: Imagen) {

            

        }
    }
}