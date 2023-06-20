package com.gvldc.vetclinic.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gvldc.vetclinic.databinding.ItemAppointmentBinding
import com.gvldc.vetclinic.databinding.ItemHeaderBinding
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.utils.ItemTypes
import com.gvldc.vetclinic.viewholders.AppointmentViewHolder
import com.gvldc.vetclinic.viewholders.HeaderViewHolder


class SharedAdapter: RecyclerView.Adapter<ViewHolder>() {

    private var adapterData: MutableList<RVDataModels> = mutableListOf()

    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingAppointment: ItemAppointmentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when(viewType){
            ItemTypes.HEADER -> {
                bindingHeader = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(bindingHeader)
            }

            ItemTypes.APPOINTMENT -> {
                bindingAppointment = ItemAppointmentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AppointmentViewHolder(bindingAppointment)
            }

            else -> {
                bindingAppointment = ItemAppointmentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                AppointmentViewHolder(bindingAppointment)
            }
        }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = adapterData[position]) {
            is RVDataModels.ItemHeader -> ItemTypes.HEADER
            is RVDataModels.ItemFutureAppointment -> ItemTypes.APPOINTMENT
            else -> throw IllegalArgumentException("Unsupported item type: ${item.javaClass.simpleName}")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = adapterData[position]) {
            is RVDataModels.ItemHeader -> {
                val headerViewHolder = holder as HeaderViewHolder
                headerViewHolder.bind(item)
            }
            is RVDataModels.ItemFutureAppointment -> {
                val appointment = holder as AppointmentViewHolder
                appointment.bind(item)
            }
            else -> throw IllegalArgumentException("Unsupported item type: ${item.javaClass.simpleName}")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: MutableList<RVDataModels>) {
        adapterData = newData
        notifyDataSetChanged()
    }
}
