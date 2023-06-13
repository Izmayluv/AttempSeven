package com.gvldc.vetclinic.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemHeaderBinding
import com.gvldc.vetclinic.databinding.ItemNotificationBinding
import com.gvldc.vetclinic.models.ItemTypes
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import com.gvldc.vetclinic.viewholders.HeaderViewHolder
import com.gvldc.vetclinic.viewholders.NotificationViewHolder

class NotificationsAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var adapterData: MutableList<RecyclerViewDataModels> = mutableListOf<RecyclerViewDataModels>()

    private lateinit var bindingHeader: ItemHeaderBinding
    private lateinit var bindingNotification: ItemNotificationBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {

            ItemTypes.HEADER -> {
                bindingHeader = ItemHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                HeaderViewHolder(bindingHeader)
            }

            else -> {
                bindingNotification = ItemNotificationBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                NotificationViewHolder(bindingNotification)
            }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is NotificationViewHolder -> {
                val notification = adapterData[position] as RecyclerViewDataModels.ItemNotification
                holder.bind(notification)
            }
            is HeaderViewHolder -> {
                val header = adapterData[position] as RecyclerViewDataModels.ItemHeader
                holder.bind(header)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is RecyclerViewDataModels.ItemNotification -> ItemTypes.NOTIFICATION
            else -> ItemTypes.HEADER
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