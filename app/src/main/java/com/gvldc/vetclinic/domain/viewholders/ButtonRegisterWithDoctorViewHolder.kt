package com.gvldc.vetclinic.domain.viewholders
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.databinding.ItemButtonRegisterWithDoctorBinding
import com.gvldc.vetclinic.presentation.interfaces.RecyclerViewItemClickListener
import com.gvldc.vetclinic.domain.models.RecyclerViewDataModels

class ButtonRegisterWithDoctorViewHolder(
    private val binding: ItemButtonRegisterWithDoctorBinding,
    private val listener: RecyclerViewItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(appointment: RecyclerViewDataModels.ItemAppointmentWDoctor){

        binding.apply {
            textView.text = appointment.action
            MyUtils.loadImageFromUrl(
                appointment.imageUrl,
                imageView
            )
            root.setOnClickListener {
                listener.onRecyclerViewItemClick(adapterPosition)
            }
        }
    }
}