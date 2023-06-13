package com.gvldc.vetclinic.viewholders
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemButtonAboutClinicBinding
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.interfaces.ItemClinicsInfoClickListener
import com.gvldc.vetclinic.models.RecyclerViewDataModels

class ButtonClinicsInfoViewHolder(
    private val binding: ItemButtonAboutClinicBinding,
    private val listener: ItemClinicsInfoClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clinics: RecyclerViewDataModels.ItemClinics){

        binding.apply {
            textView.text = clinics.action
            MyUtils.loadImageFromUrl(
                clinics.imageUrl,
                imageView
            )
            root.setOnClickListener {
                listener.onRecyclerViewClinicsInfoClick(adapterPosition)
            }
        }
    }
}