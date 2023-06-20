package com.gvldc.vetclinic.viewholders

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemAppointmentBinding
import com.gvldc.vetclinic.models.RVDataModels

class AppointmentViewHolder(
    private val binding: ItemAppointmentBinding
): RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(appointment: RVDataModels.ItemFutureAppointment){
        binding.apply {
            tvAppointmentService.text = appointment.serviceName
            tvAnalysisDate.text = appointment.date
            tvAppointmentText.text = "Вы записаны на ${appointment.serviceName} " +
                    "в клинике на ${appointment.clinicName} в ${appointment.futureAppointmentDateTime}\n\n" +
                    "Ветеринар - ${appointment.vetName}"
        }
    }
}