package com.gvldc.vetclinic.viewholders
import androidx.recyclerview.widget.RecyclerView
import com.gvldc.vetclinic.databinding.ItemButtonAboutVetsBinding
import com.gvldc.vetclinic.databinding.ItemNotificationBinding
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.interfaces.ItemVetsInfoClickListener
import com.gvldc.vetclinic.models.RecyclerViewDataModels

class NotificationViewHolder(
    private val binding: ItemNotificationBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(notifications: RecyclerViewDataModels.ItemNotification){

        binding.apply {
            textViewNotificationTitle.text = notifications.title
            textViewMessage.text = notifications.message
            textViewDateTime.text = notifications.datetime
        }
    }
}