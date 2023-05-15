package com.example.attempseven.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.utils.MyUtils
import com.example.attempseven.databinding.ItemServiceBinding
import com.example.attempseven.interfaces.RecyclerViewItemClickListener
import com.example.attempseven.models.RecyclerViewDataModels

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