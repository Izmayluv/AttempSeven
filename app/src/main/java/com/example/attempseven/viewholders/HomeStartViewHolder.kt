package com.example.attempseven.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.utils.MyUtils
import com.example.attempseven.models.RecyclerViewDataModels
import com.example.attempseven.databinding.ItemHomeStartBinding


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