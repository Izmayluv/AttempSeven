package com.gvldc.vetclinic.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemPromoBinding
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.utils.MyUtils

class ChildViewHolder(private val binding: ItemPromoBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(childModel: RVDataModels.ChildModel){
       // binding.textPromo.text = childModel.imageUrl
        binding.apply {
            MyUtils.loadImageFromUrlFit(
                childModel.imageUrl,
                imgPromo
            )
        }
    }

}