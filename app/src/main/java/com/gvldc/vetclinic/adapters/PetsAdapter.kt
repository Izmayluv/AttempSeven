package com.gvldc.vetclinic.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemPetBinding
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import com.gvldc.vetclinic.utils.ItemTypes.HEADER
import com.gvldc.vetclinic.utils.ItemTypes.PET
import com.gvldc.vetclinic.databinding.ItemHeaderBinding
import com.gvldc.vetclinic.fragments.PetsFragment
import com.gvldc.vetclinic.viewholders.HeaderViewHolder
import com.gvldc.vetclinic.viewholders.PetViewHolder

class PetsAdapter(
    private val listener: PetsFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapterData: MutableList<RecyclerViewDataModels> = mutableListOf<RecyclerViewDataModels>()

    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingPet: ItemPetBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            PET -> {
                bindingPet = ItemPetBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                PetViewHolder(bindingPet, listener)
            }

            HEADER -> {
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
            is RecyclerViewDataModels.ItemPet -> PET
            else -> HEADER
        }
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: MutableList<RecyclerViewDataModels>) {
        adapterData = newData
        notifyDataSetChanged()
    }
}