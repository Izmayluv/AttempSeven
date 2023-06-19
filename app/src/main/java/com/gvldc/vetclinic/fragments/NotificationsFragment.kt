package com.gvldc.vetclinic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.adapters.NotificationsAdapter
import com.gvldc.vetclinic.adapters.PetsAdapter
import com.gvldc.vetclinic.databinding.FragmentNotificationsBinding
import com.gvldc.vetclinic.viewmodels.ViewModel


class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    private val viewModel by activityViewModels<ViewModel>()

    lateinit var bindingFragmentNotifications : FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentNotifications = FragmentNotificationsBinding.inflate(layoutInflater)

        val adapter = NotificationsAdapter()
        viewModel.getNotificationsData(FirebaseAuth.getInstance().currentUser?.uid.toString()) { data ->
            adapter.setData(data)
        }

        val notificationManager = NotificationManagerCompat.from(bindingFragmentNotifications.root.context)
        notificationManager.cancel(123)

        bindingFragmentNotifications.rvNotifications.apply {
            layoutManager = LinearLayoutManager(this@NotificationsFragment.context)
            hasFixedSize()
            this.adapter = adapter
        }

        return bindingFragmentNotifications.root
    }

    override fun onResume() {
        super.onResume()
        val notificationManager = NotificationManagerCompat.from(bindingFragmentNotifications.root.context)
        notificationManager.cancel(123)
    }

}