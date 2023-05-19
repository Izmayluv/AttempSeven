package com.gvldc.vetclinic.domain.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.domain.models.ItemTypes
import com.gvldc.vetclinic.databinding.ItemHeaderBinding
import com.gvldc.vetclinic.databinding.ItemServiceBinding
import com.gvldc.vetclinic.domain.viewholders.HeaderViewHolder
import com.gvldc.vetclinic.domain.viewholders.ServiceViewHolder
import com.gvldc.vetclinic.presentation.interfaces.RecyclerViewItemClickListener
import com.gvldc.vetclinic.domain.models.RecyclerViewDataModels

class ServicesAdapter(
    private val listener: RecyclerViewItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapterData: MutableList<RecyclerViewDataModels> = mutableListOf<RecyclerViewDataModels>()

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ServiceViewHolder -> {
                val service = adapterData[position] as RecyclerViewDataModels.ItemService
                holder.bind(service)
            }
            is HeaderViewHolder -> {
                val header = adapterData[position] as RecyclerViewDataModels.ItemHeader
                holder.bind(header)
            }
        }
    }

    override fun getItemCount(): Int {
       return adapterData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is RecyclerViewDataModels.ItemService -> ItemTypes.SERVICE
            else -> ItemTypes.HEADER
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: MutableList<RecyclerViewDataModels>) {
        adapterData = newData
        notifyDataSetChanged()
    }
}