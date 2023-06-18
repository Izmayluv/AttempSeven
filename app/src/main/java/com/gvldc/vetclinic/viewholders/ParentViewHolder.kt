package com.gvldc.vetclinic.viewholders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.adapters.ChildAdapter
import com.gvldc.vetclinic.databinding.ChildRvBinding
import com.gvldc.vetclinic.models.RVDataModels

class ParentViewHolder (val binding: ChildRvBinding) : RecyclerView.ViewHolder(binding.root) {


    private val childAdapter = ChildAdapter()

    fun bind(childData: List<RVDataModels.ChildModel>) {
        binding.rvChild.apply {
            layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            adapter = childAdapter
        }
        childAdapter.setData(childData)
    }
}