package com.example.attempseven.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.models.RecyclerViewDataModels
import com.example.attempseven.databinding.ItemHeaderBinding

class HeaderViewHolder(
    private val binding: ItemHeaderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(header: RecyclerViewDataModels.ItemHeader){
        binding.tvTitle.text = header.title
    }
}