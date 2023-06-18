package com.gvldc.vetclinic.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemVetInfoBinding
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.viewholders.VetsInfoViewHolder

class VetsInfoAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var bindingVetInfo: ItemVetInfoBinding
    private var adapterData: MutableList<RVDataModels.VetInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType){

            else -> {
                bindingVetInfo = ItemVetInfoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                VetsInfoViewHolder(bindingVetInfo)
            }
        }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = adapterData[position]) {

            else -> {
                val viewHolder = holder as VetsInfoViewHolder
                viewHolder.bind(item)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<RVDataModels.VetInfo>) {
        adapterData = data
        notifyDataSetChanged()
    }

}