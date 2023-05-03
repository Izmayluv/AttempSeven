package com.example.attempseven.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.MyUtils
import com.example.attempseven.data.PetAdapterDataModel
import com.example.attempseven.databinding.ItemPetBinding

class PetViewHolder(
    private val binding: ItemPetBinding
): RecyclerView.ViewHolder(
    binding.root
) {
    fun bind(pet: PetAdapterDataModel.Pet){
        binding.apply {
            tvName.text = pet.name
            tvAge.text = pet.birthDay.toString()
            tvBreed.text = pet.breed

            MyUtils.loadImageFromUrl(
                pet.imageUrl,
                imageViewCat
            )
        }
    }
}