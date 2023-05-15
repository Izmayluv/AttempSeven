package com.example.attempseven.holders

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.MyUtils
import com.example.attempseven.models.RecyclerViewDataModels
import com.example.attempseven.databinding.ItemPetBinding
import com.example.attempseven.interfaces.RecyclerViewItemClickListener

class PetViewHolder(
    private val binding: ItemPetBinding,
    private val listener: RecyclerViewItemClickListener
): RecyclerView.ViewHolder(
    binding.root
){
    @RequiresApi(Build.VERSION_CODES.O)
    fun bind(pet: RecyclerViewDataModels.ItemPet){

        binding.apply {
            tvName.text = pet.name
            tvAge.text = MyUtils.currentAge(pet.birthDay)
            tvBreed.text = pet.breed

            MyUtils.loadImageFromUrl(
                pet.imageUrl,
                imageViewCat
            )

            root.setOnClickListener {
                listener.onRecyclerViewItemClick(adapterPosition)
            }
        }
    }
}