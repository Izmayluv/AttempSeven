package com.gvldc.vetclinic.domain.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.domain.models.RecyclerViewDataModels
import com.gvldc.vetclinic.databinding.ItemHeaderBinding

class HeaderViewHolder(
    private val binding: ItemHeaderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(header: RecyclerViewDataModels.ItemHeader){
        binding.tvTitle.text = header.title
    }
}