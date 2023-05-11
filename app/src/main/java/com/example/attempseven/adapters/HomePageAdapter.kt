package com.example.attempseven.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.models.DataModel
import com.example.attempseven.data.ItemTypes
import com.example.attempseven.databinding.ItemHeaderBinding
import com.example.attempseven.databinding.ItemHomeStartBinding
import com.example.attempseven.holders.HeaderViewHolder
import com.example.attempseven.holders.HomeStartViewHolder

class HomePageAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val adapterData: MutableList<DataModel> = mutableListOf<DataModel>()

    private lateinit var bindingStart: ItemHomeStartBinding
    private lateinit var bindingHeader: ItemHeaderBinding
    //private lateinit var bindingPet: ItemPetBinding

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
/*
            ItemTypes.TYPE_PET -> {
                bindingPet = ItemPetBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PetViewHolder(bindingPet)
            }*/

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
            is DataModel.ItemHomeStart -> ItemTypes.TYPE_START
            is DataModel.ItemHeader -> ItemTypes.TYPE_HEADER
            is DataModel.ItemPet -> ItemTypes.TYPE_PET
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when(holder){
            is HeaderViewHolder -> {
                val header = adapterData[position] as DataModel.ItemHeader
                holder.bind(header)
            }

            is HomeStartViewHolder -> {
                val homeStart = adapterData[position] as DataModel.ItemHomeStart
                holder.bind(homeStart)
            }

/*            is PetViewHolder -> {
                val pet = adapterData[position] as DataModel.ItemPet
                holder.bind(pet, listener = )
            }*/
        }
    }

    fun setData(data: List<DataModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }

}