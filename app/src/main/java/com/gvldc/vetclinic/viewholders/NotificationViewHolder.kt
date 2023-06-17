package com.gvldc.vetclinic.viewholders
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemNotificationBinding
import com.gvldc.vetclinic.models.RVDataModels

class NotificationViewHolder(
    private val binding: ItemNotificationBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(notifications: RVDataModels.ItemNotification){

        binding.apply {
            textViewNotificationTitle.text = notifications.title
            textViewMessage.text = notifications.message
            textViewDateTime.text = notifications.datetime
        }
    }
}