package com.example.attempseven.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.models.DataModel
import com.example.attempseven.databinding.ItemHeaderBinding

class HeaderViewHolder(
    private val binding: ItemHeaderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(header: DataModel.ItemHeader){
        binding.tvTitle.text = header.title
    }
}