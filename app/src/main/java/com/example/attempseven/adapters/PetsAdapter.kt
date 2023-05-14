package com.example.attempseven.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.databinding.ItemPetBinding
import com.example.attempseven.models.RecyclerViewDataModels
import com.example.attempseven.data.ItemTypes.TYPE_HEADER
import com.example.attempseven.data.ItemTypes.TYPE_PET
import com.example.attempseven.databinding.ItemHeaderBinding
import com.example.attempseven.holders.HeaderViewHolder
import com.example.attempseven.holders.PetViewHolder
import com.example.attempseven.interfaces.RecyclerViewItemClickListener

class PetsAdapter(
    private val listener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingPet: ItemPetBinding
    private var adapterData: MutableList<RecyclerViewDataModels> = mutableListOf<RecyclerViewDataModels>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_PET -> {
                bindingPet = ItemPetBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PetViewHolder(bindingPet, listener)
            }

            TYPE_HEADER -> {
                bindingHeader = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(bindingHeader)
            }

            else -> {
                bindingPet = ItemPetBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PetViewHolder(bindingPet, listener)
            }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is PetViewHolder -> {
                val pet = adapterData[position] as RecyclerViewDataModels.ItemPet
                holder.bind(pet)
            }
            is HeaderViewHolder -> {
                val header = adapterData[position] as RecyclerViewDataModels.ItemHeader
                holder.bind(header)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is RecyclerViewDataModels.ItemPet -> TYPE_PET
            else -> TYPE_HEADER
        }
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: MutableList<RecyclerViewDataModels>) {
        /*adapterData.apply {
            clear()
            addAll(newData)
        }*/
        adapterData = newData
        notifyDataSetChanged()
    }
}