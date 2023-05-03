package com.example.attempseven.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.attempseven.databinding.ItemPetBinding
import com.example.attempseven.data.PetAdapterDataModel
import com.example.attempseven.databinding.ItemHeaderBinding
import com.example.attempseven.holders.HeaderViewHolder
import com.example.attempseven.holders.PetViewHolder

class PetAdapter(
    private val adapterData: MutableList<PetAdapterDataModel> = mutableListOf<PetAdapterDataModel>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingPet: ItemPetBinding

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

    override fun getItemCount(): Int {
        return adapterData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is PetAdapterDataModel.Pet -> TYPE_PET
            else -> TYPE_HEADER
        }
    }

    fun setData(data: List<PetAdapterDataModel>) {
        adapterData.apply {
            clear()
            addAll(data)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is PetViewHolder -> {
                val pet = adapterData[position] as PetAdapterDataModel.Pet
                holder.bind(pet)
            }
            is HeaderViewHolder -> {
                val header = adapterData[position] as PetAdapterDataModel.Header
                holder.bind(header)
            }
        }
    }

    companion object {
        private const val TYPE_PET = 0
        private const val TYPE_HEADER = 1
    }
}