package com.gvldc.vetclinic.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.databinding.ItemPetBinding
import com.gvldc.vetclinic.fragments.PetsFragment

class PetViewHolder(
    private val binding: ItemPetBinding,
    private val listener: PetsFragment
): RecyclerView.ViewHolder(
    binding.root
){

    fun bind(pet: RVDataModels.ItemPet){

        binding.apply {
            tvName.text = pet.name
            tvAge.text = MyUtils.stringToTimestamp(pet.birthDay,"dd.MM.yyyy")
                ?.let { MyUtils.calculateAge(it) }
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