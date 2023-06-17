package com.gvldc.vetclinic.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.utils.ItemTypes
import com.gvldc.vetclinic.databinding.ItemHeaderBinding
import com.gvldc.vetclinic.databinding.ItemServiceBinding
import com.gvldc.vetclinic.fragments.ChooseServiceFragment
import com.gvldc.vetclinic.viewholders.HeaderViewHolder
import com.gvldc.vetclinic.viewholders.ServiceViewHolder
import com.gvldc.vetclinic.models.RVDataModels

class ServicesAdapter(
    private val listener: ChooseServiceFragment
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapterData: MutableList<RVDataModels> = mutableListOf<RVDataModels>()

    private lateinit var bindingService: ItemServiceBinding
    private lateinit var bindingHeader: ItemHeaderBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType){
            ItemTypes.SERVICE -> {
                bindingService = ItemServiceBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ServiceViewHolder(bindingService, listener)
            }

            else -> {
                bindingHeader = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(bindingHeader)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ServiceViewHolder -> {
                val service = adapterData[position] as RVDataModels.ItemService
                holder.bind(service)
            }
            is HeaderViewHolder -> {
                val header = adapterData[position] as RVDataModels.ItemHeader
                holder.bind(header)
            }
        }
    }

    override fun getItemCount(): Int {
       return adapterData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is RVDataModels.ItemService -> ItemTypes.SERVICE
            else -> ItemTypes.HEADER
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: MutableList<RVDataModels>) {
        adapterData = newData
        notifyDataSetChanged()
    }
}