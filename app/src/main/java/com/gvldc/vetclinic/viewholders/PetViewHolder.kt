package com.gvldc.vetclinic.viewholders

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import com.gvldc.vetclinic.databinding.ItemPetBinding
import com.gvldc.vetclinic.interfaces.RecyclerViewItemClickListener

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