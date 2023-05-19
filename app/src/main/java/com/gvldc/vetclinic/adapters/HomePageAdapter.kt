package com.gvldc.vetclinic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import com.gvldc.vetclinic.models.ItemTypes
import com.gvldc.vetclinic.databinding.ItemButtonRegisterWithDoctorBinding
import com.gvldc.vetclinic.databinding.ItemHeaderBinding
import com.gvldc.vetclinic.databinding.ItemHomeStartBinding
import com.gvldc.vetclinic.viewholders.ButtonRegisterWithDoctorViewHolder
import com.gvldc.vetclinic.viewholders.HeaderViewHolder
import com.gvldc.vetclinic.viewholders.HomeStartViewHolder
import com.gvldc.vetclinic.interfaces.RecyclerViewItemClickListener

class HomePageAdapter(
    private val listener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val adapterData: MutableList<RecyclerViewDataModels> = mutableListOf<RecyclerViewDataModels>()

    private lateinit var bindingStart: ItemHomeStartBinding
    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingAppointment: ItemButtonRegisterWithDoctorBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType){
            ItemTypes.HOME_START -> {
                bindingStart = ItemHomeStartBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HomeStartViewHolder(bindingStart)
            }

            ItemTypes.HEADER -> {
                bindingHeader = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(bindingHeader)
            }

            ItemTypes.BTN_APPOINTMENT_DOCTOR -> {
                bindingAppointment = ItemButtonRegisterWithDoctorBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ButtonRegisterWithDoctorViewHolder(bindingAppointment, listener)
            }

            else -> {
                bindingStart = ItemHomeStartBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HomeStartViewHolder(bindingStart)
            }
        }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is RecyclerViewDataModels.ItemHomeStart -> ItemTypes.HOME_START
            is RecyclerViewDataModels.ItemHeader -> ItemTypes.HEADER
            is RecyclerViewDataModels.ItemAppointmentWDoctor -> ItemTypes.BTN_APPOINTMENT_DOCTOR
            else -> ItemTypes.PET
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when(holder){
            is HeaderViewHolder -> {
                val header = adapterData[position] as RecyclerViewDataModels.ItemHeader
                holder.bind(header)
            }

            is HomeStartViewHolder -> {
                val homeStart = adapterData[position] as RecyclerViewDataModels.ItemHomeStart
                holder.bind(homeStart)
            }

            is ButtonRegisterWithDoctorViewHolder -> {
                val appointment = adapterData[position] as RecyclerViewDataModels.ItemAppointmentWDoctor
                holder.bind(appointment)
            }

        }
    }

    fun setData(data: List<RecyclerViewDataModels>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }

}