package com.gvldc.vetclinic.viewholders
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemButtonAboutVetsBinding
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.interfaces.ItemVetsInfoClickListener
import com.gvldc.vetclinic.models.RVDataModels

class ButtonVetsInfoViewHolder(
    private val binding: ItemButtonAboutVetsBinding,
    private val listener: ItemVetsInfoClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(vets: RVDataModels.ItemVets){

        binding.apply {
            textView.text = vets.action
            MyUtils.loadImageFromUrl(
                vets.imageUrl,
                imageView
            )
            root.setOnClickListener {
                listener.onRecyclerViewVetsInfoClick(adapterPosition)
            }
        }
    }
}