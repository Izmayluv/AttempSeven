package com.example.attempseven.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.databinding.ItemPetBinding
import com.example.attempseven.models.DataModel
import com.example.attempseven.data.ItemTypes.TYPE_HEADER
import com.example.attempseven.data.ItemTypes.TYPE_PET
import com.example.attempseven.databinding.ItemHeaderBinding
import com.example.attempseven.holders.HeaderViewHolder
import com.example.attempseven.holders.PetViewHolder
import com.example.attempseven.interfaces.OnItemClickListener

class PetsAdapter(
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingPet: ItemPetBinding
    private val adapterData: MutableList<DataModel> = mutableListOf<DataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_PET -> {
                bindingPet = ItemPetBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PetViewHolder(bindingPet)
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
                PetViewHolder(bindingPet)
            }
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is PetViewHolder -> {
                val pet = adapterData[position] as DataModel.ItemPet
                holder.bind(pet, listener = listener)
            }
            is HeaderViewHolder -> {
                val header = adapterData[position] as DataModel.ItemHeader
                holder.bind(header)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is DataModel.ItemPet -> TYPE_PET
            else -> TYPE_HEADER
        }
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    fun setData(data: List<DataModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }
}