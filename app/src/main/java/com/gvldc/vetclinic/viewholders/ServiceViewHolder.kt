package com.gvldc.vetclinic.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.databinding.ItemServiceBinding
import com.gvldc.vetclinic.fragments.ChooseServiceFragment
import com.gvldc.vetclinic.models.RVDataModels

class ServiceViewHolder(
    private val binding: ItemServiceBinding,
    private val listener: ChooseServiceFragment
): RecyclerView.ViewHolder(binding.root) {

    fun bind(service: RVDataModels.ItemService){
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