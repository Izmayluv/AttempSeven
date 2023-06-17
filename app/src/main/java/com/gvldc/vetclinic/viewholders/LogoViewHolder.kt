package com.gvldc.vetclinic.viewholders
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemLogoBinding
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.models.RVDataModels

class LogoViewHolder(
    private val binding: ItemLogoBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(logo: RVDataModels.ItemLogo){

        binding.apply {
            MyUtils.loadImageFromUrlFit(
                logo.imageUrl,
                imageView
            )
        }
    }
}