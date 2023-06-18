package com.gvldc.vetclinic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemPromoBinding
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.utils.ItemTypes
import com.gvldc.vetclinic.viewholders.ChildViewHolder

class ChildAdapter(
    private val childAdapterData: MutableList<RVDataModels.ChildModel> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var bindingItemPromo: ItemPromoBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType){
            ItemTypes.CHILD_RECYCLER -> {
                bindingItemPromo = ItemPromoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ChildViewHolder(bindingItemPromo)
            }

            else -> {
                bindingItemPromo = ItemPromoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ChildViewHolder(bindingItemPromo)
            }
        }

    override fun getItemCount(): Int {
        return childAdapterData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = childAdapterData[position]) {

            else -> {
                val childViewHolder = holder as ChildViewHolder
                childViewHolder.bind(item)
            }
        }
    }

    fun setData(data: List<RVDataModels.ChildModel>) {
        childAdapterData.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

}