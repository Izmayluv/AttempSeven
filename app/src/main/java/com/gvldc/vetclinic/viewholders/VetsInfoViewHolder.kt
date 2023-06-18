package com.gvldc.vetclinic.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemHeaderBinding
import com.gvldc.vetclinic.databinding.ItemVetInfoBinding
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.utils.MyUtils

class VetsInfoViewHolder ( private val binding: ItemVetInfoBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(vetInfo: RVDataModels.VetInfo){
        binding.apply {
            MyUtils.loadImageFromUrlFit(
                vetInfo.imageUrl,
                imageView
            )
            textViewVetName.text = vetInfo.name
            textViewVetEducationText.text = vetInfo.education
            textViewVetSpeciesText.text = vetInfo.species
            textViewVetReceptionFromText.text =vetInfo.receptionFrom
        }
    }
}