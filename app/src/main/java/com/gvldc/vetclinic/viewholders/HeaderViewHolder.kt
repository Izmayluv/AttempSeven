package com.gvldc.vetclinic.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.databinding.ItemHeaderBinding

class HeaderViewHolder(
    private val binding: ItemHeaderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(header: RVDataModels.ItemHeader){
        binding.tvTitle.text = header.title
    }
}