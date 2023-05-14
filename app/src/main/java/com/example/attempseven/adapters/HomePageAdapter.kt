package com.example.attempseven.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.models.RecyclerViewDataModels
import com.example.attempseven.data.ItemTypes
import com.example.attempseven.databinding.ItemButtonRegisterWithDoctorBinding
import com.example.attempseven.databinding.ItemHeaderBinding
import com.example.attempseven.databinding.ItemHomeStartBinding
import com.example.attempseven.holders.ButtonRegisterWithDoctorViewHolder
import com.example.attempseven.holders.HeaderViewHolder
import com.example.attempseven.holders.HomeStartViewHolder
import com.example.attempseven.interfaces.RecyclerViewItemClickListener

class HomePageAdapter(
    private val listener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val adapterData: MutableList<RecyclerViewDataModels> = mutableListOf<RecyclerViewDataModels>()

    private lateinit var bindingStart: ItemHomeStartBinding
    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingAppointment: ItemButtonRegisterWithDoctorBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType){
            ItemTypes.TYPE_START -> {
                bindingStart = ItemHomeStartBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HomeStartViewHolder(bindingStart)
            }

            ItemTypes.TYPE_HEADER -> {
                bindingHeader = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(bindingHeader)
            }

            ItemTypes.TYPE_APPOINTMENT_W_DOCTOR -> {
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
            is RecyclerViewDataModels.ItemHomeStart -> ItemTypes.TYPE_START
            is RecyclerViewDataModels.ItemHeader -> ItemTypes.TYPE_HEADER
            is RecyclerViewDataModels.ItemAppointmentWDoctor -> ItemTypes.TYPE_APPOINTMENT_W_DOCTOR
            else -> ItemTypes.TYPE_PET
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