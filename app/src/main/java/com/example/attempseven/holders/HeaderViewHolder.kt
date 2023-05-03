package com.example.attempseven.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.data.PetAdapterDataModel
import com.example.attempseven.databinding.ItemHeaderBinding

class HeaderViewHolder(
    private val binding: ItemHeaderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(header: PetAdapterDataModel.Header){
        binding.tvTitle.text = header.title
    }
}