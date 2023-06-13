package com.gvldc.vetclinic.viewholders
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemButtonAppointmentBinding
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.interfaces.ItemAppointmentClickListener
import com.gvldc.vetclinic.models.RecyclerViewDataModels

class ButtonAppointmentViewHolder(
    private val binding: ItemButtonAppointmentBinding,
    private val listener: ItemAppointmentClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(appointment: RecyclerViewDataModels.ItemAppointment){

        binding.apply {
            textView.text = appointment.action
            MyUtils.loadImageFromUrl(
                appointment.imageUrl,
                imageView
            )
            root.setOnClickListener {
                listener.onRecyclerViewAppointmentClick(adapterPosition)
            }
        }
    }
}