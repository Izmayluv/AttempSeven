package com.example.attempseven.holders
import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.MyUtils
import com.example.attempseven.databinding.ItemButtonRegisterWithDoctorBinding
import com.example.attempseven.interfaces.RecyclerViewItemClickListener
import com.example.attempseven.models.RecyclerViewDataModels

class ButtonRegisterWithDoctorViewHolder(
    private val binding: ItemButtonRegisterWithDoctorBinding,
    private val listener: RecyclerViewItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(appointment: RecyclerViewDataModels.ItemAppointmentWDoctor){

        binding.apply {
            textView.text = appointment.action
            MyUtils.loadImageFromUrl(
                appointment.image,
                imageView
            )
            root.setOnClickListener {
                listener.onRecyclerViewItemClick(adapterPosition)
            }
        }
    }
}