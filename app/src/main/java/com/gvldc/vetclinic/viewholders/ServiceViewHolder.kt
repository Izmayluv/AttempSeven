package com.gvldc.vetclinic.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.databinding.ItemServiceBinding
import com.gvldc.vetclinic.interfaces.RecyclerViewItemClickListener
import com.gvldc.vetclinic.models.RecyclerViewDataModels

class ServiceViewHolder(
    private val binding: ItemServiceBinding,
    private val listener: RecyclerViewItemClickListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(service: RecyclerViewDataModels.ItemService){
        binding.apply {
            tvServiceDirection.text = service.serviceDirection
            tvDescription.text = service.description

            MyUtils.loadImageFromUrl(
                service.imageUrl,
                imageService
            )

            root.setOnClickListener {
                listener.onRecyclerViewItemClick(adapterPosition)
            }
        }
    }
}