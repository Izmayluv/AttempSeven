package com.gvldc.vetclinic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemButtonAboutClinicBinding
import com.gvldc.vetclinic.databinding.ItemButtonAboutVetsBinding
import com.gvldc.vetclinic.databinding.ItemButtonAppointmentBinding
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import com.gvldc.vetclinic.utils.ItemTypes
import com.gvldc.vetclinic.databinding.ItemHeaderBinding
import com.gvldc.vetclinic.databinding.ItemHomeStartBinding
import com.gvldc.vetclinic.databinding.ItemLogoBinding
import com.gvldc.vetclinic.databinding.ItemNewsBinding
import com.gvldc.vetclinic.viewholders.ButtonAppointmentViewHolder
import com.gvldc.vetclinic.viewholders.HeaderViewHolder
import com.gvldc.vetclinic.viewholders.HomeStartViewHolder
import com.gvldc.vetclinic.interfaces.ItemAppointmentClickListener
import com.gvldc.vetclinic.interfaces.ItemClinicsInfoClickListener
import com.gvldc.vetclinic.interfaces.ItemVetsInfoClickListener
import com.gvldc.vetclinic.viewholders.ButtonClinicsInfoViewHolder
import com.gvldc.vetclinic.viewholders.ButtonVetsInfoViewHolder
import com.gvldc.vetclinic.viewholders.LogoViewHolder
import com.gvldc.vetclinic.viewholders.NewsViewHolder

class HomePageAdapter(
    private val appointmentClickListener: ItemAppointmentClickListener,
    private val vetsInfoClickListener: ItemVetsInfoClickListener,
    private val clinicsInfoClickListener: ItemClinicsInfoClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val adapterData: MutableList<RecyclerViewDataModels> = mutableListOf<RecyclerViewDataModels>()

    private lateinit var bindingStart: ItemHomeStartBinding
    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingAppointment: ItemButtonAppointmentBinding
    private lateinit var bindingClinics: ItemButtonAboutClinicBinding
    private lateinit var bindingVets: ItemButtonAboutVetsBinding
    private lateinit var bindingLogo: ItemLogoBinding
    private lateinit var bindingNews: ItemNewsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType){

            ItemTypes.HEADER -> {
                bindingHeader = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(bindingHeader)
            }

            ItemTypes.BTN_CLINICS -> {
                bindingClinics = ItemButtonAboutClinicBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ButtonClinicsInfoViewHolder(bindingClinics, clinicsInfoClickListener)
            }

            ItemTypes.BTN_VETS -> {
                bindingVets = ItemButtonAboutVetsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ButtonVetsInfoViewHolder(bindingVets, vetsInfoClickListener)
            }

            ItemTypes.BTN_APPOINTMENT -> {
                bindingAppointment = ItemButtonAppointmentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ButtonAppointmentViewHolder(bindingAppointment, appointmentClickListener)
            }

            ItemTypes.LOGO -> {
                bindingLogo = ItemLogoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                LogoViewHolder(bindingLogo)
            }

            ItemTypes.NEWS -> {
                bindingNews = ItemNewsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                NewsViewHolder(bindingNews)
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
            is RecyclerViewDataModels.ItemAppointment -> ItemTypes.BTN_APPOINTMENT
            is RecyclerViewDataModels.ItemVets -> ItemTypes.BTN_VETS
            is RecyclerViewDataModels.ItemClinics -> ItemTypes.BTN_CLINICS
            is RecyclerViewDataModels.ItemLogo -> ItemTypes.LOGO
            is RecyclerViewDataModels.ItemNews -> ItemTypes.NEWS
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

            is ButtonAppointmentViewHolder -> {
                val homeButton = adapterData[position] as RecyclerViewDataModels.ItemAppointment
                holder.bind(homeButton)
            }

            is ButtonClinicsInfoViewHolder -> {
                val clinics = adapterData[position] as RecyclerViewDataModels.ItemClinics
                holder.bind(clinics)
            }

            is ButtonVetsInfoViewHolder -> {
                val vets = adapterData[position] as RecyclerViewDataModels.ItemVets
                holder.bind(vets)
            }

            is LogoViewHolder -> {
                val logo = adapterData[position] as RecyclerViewDataModels.ItemLogo
                holder.bind(logo)
            }

            is NewsViewHolder -> {
                val news = adapterData[position] as RecyclerViewDataModels.ItemNews
                holder.bind(news)
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