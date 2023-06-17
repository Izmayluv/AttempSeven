package com.gvldc.vetclinic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ChildRvBinding
import com.gvldc.vetclinic.databinding.ItemButtonAboutClinicBinding
import com.gvldc.vetclinic.databinding.ItemButtonAboutVetsBinding
import com.gvldc.vetclinic.databinding.ItemButtonAppointmentBinding
import com.gvldc.vetclinic.models.RVDataModels
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
import com.gvldc.vetclinic.viewholders.ParentViewHolder

class ParentAdapter(
    private val appointmentClickListener: ItemAppointmentClickListener,
    private val vetsInfoClickListener: ItemVetsInfoClickListener,
    private val clinicsInfoClickListener: ItemClinicsInfoClickListener,


) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val adapterData: MutableList<RVDataModels> = mutableListOf()


    private lateinit var bindingStart: ItemHomeStartBinding
    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingAppointment: ItemButtonAppointmentBinding
    private lateinit var bindingClinics: ItemButtonAboutClinicBinding
    private lateinit var bindingVets: ItemButtonAboutVetsBinding
    private lateinit var bindingLogo: ItemLogoBinding
    private lateinit var bindingNews: ItemNewsBinding
    private lateinit var bindingChildRv: ChildRvBinding

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

            ItemTypes.CHILD_RECYCLER -> {
                bindingChildRv = ChildRvBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ParentViewHolder(bindingChildRv)
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
        return when (val item = adapterData[position]) {
            is RVDataModels.ItemHomeStart -> ItemTypes.HOME_START
            is RVDataModels.ItemHeader -> ItemTypes.HEADER
            is RVDataModels.ItemAppointment -> ItemTypes.BTN_APPOINTMENT
            is RVDataModels.ItemVets -> ItemTypes.BTN_VETS
            is RVDataModels.ItemClinics -> ItemTypes.BTN_CLINICS
            is RVDataModels.ItemLogo -> ItemTypes.LOGO
            is RVDataModels.ItemNews -> ItemTypes.NEWS
            is RVDataModels.ParentModel -> ItemTypes.CHILD_RECYCLER

            else -> throw IllegalArgumentException("Unsupported item type: ${item.javaClass.simpleName}")
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when(val item = adapterData[position]){

            is RVDataModels.ItemHeader -> {
                val headerViewHolder = holder as HeaderViewHolder
                headerViewHolder.bind(item)
            }

            /*is RVDataModels.ParentModel -> {
                val parentViewHolder = holder as ParentViewHolder
                bindChildRecyclerView(parentViewHolder, item.list)
            }*/

            is RVDataModels.ParentModel -> {
                val parentViewHolder = holder as ParentViewHolder
                parentViewHolder.bind(item.list)
            }

            is RVDataModels.ItemHomeStart -> {
                val homeStartViewHolder = holder as HomeStartViewHolder
                homeStartViewHolder.bind(item)
            }

            is RVDataModels.ItemAppointment -> {
                val appointmentViewHolder = holder as ButtonAppointmentViewHolder
                appointmentViewHolder.bind(item)
            }

            is RVDataModels.ItemClinics -> {
                val clinicsViewHolder = holder as ButtonClinicsInfoViewHolder
                clinicsViewHolder.bind(item)
            }

            is RVDataModels.ItemVets -> {
                val vetsViewHolder = holder as ButtonVetsInfoViewHolder
                vetsViewHolder.bind(item)
            }

            is RVDataModels.ItemLogo -> {
                val logoViewHolder = holder as LogoViewHolder
                logoViewHolder.bind(item)
            }

            is RVDataModels.ItemNews -> {
                val newsViewHolder = holder as NewsViewHolder
                newsViewHolder.bind(item)
            }

            else -> throw IllegalArgumentException("Unsupported item type: ${item.javaClass.simpleName}")
        }
    }

    fun setData(data: List<RVDataModels>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

}