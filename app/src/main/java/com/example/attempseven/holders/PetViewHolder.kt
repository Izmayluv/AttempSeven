package com.example.attempseven.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.MyUtils
import com.example.attempseven.models.DataModel
import com.example.attempseven.databinding.ItemPetBinding
import com.example.attempseven.interfaces.OnItemClickListener

class PetViewHolder(
    private val binding: ItemPetBinding,
): RecyclerView.ViewHolder(
    binding.root
){
    fun bind(pet: DataModel.ItemPet, listener: OnItemClickListener){

        binding.apply {
            tvName.text = pet.name
            tvAge.text = MyUtils.toSimpleString(pet.birthDay)
            tvBreed.text = pet.breed

            MyUtils.loadImageFromUrl(
                pet.imageUrl,
                imageViewCat
            )

            itemView.setOnClickListener {
                listener.onItemClick(pet)
            }
        }
    }
}