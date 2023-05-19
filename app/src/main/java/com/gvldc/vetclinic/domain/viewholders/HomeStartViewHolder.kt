package com.gvldc.vetclinic.domain.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.domain.models.RecyclerViewDataModels
import com.gvldc.vetclinic.databinding.ItemHomeStartBinding


class HomeStartViewHolder(
    var binding: ItemHomeStartBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(itemHome: RecyclerViewDataModels.ItemHomeStart){
        binding.apply {
            MyUtils.loadImageFromUrl(
                itemHome.imageUrlOne,
                ivOne
            )

/*            MyUtils.loadImageFromUrl(
                itemHome.imageUrlTwo,
                ivTwo
            )

            MyUtils.loadImageFromUrl(
                itemHome.imageUrlThree,
                ivThree
            )

            MyUtils.loadImageFromUrl(
                itemHome.imageUrlFour,
                ivFour
            )*/
        }
    }
}