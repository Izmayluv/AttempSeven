package com.gvldc.vetclinic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.databinding.FragmentNotificationsBinding


class NotificationsFragment : Fragment(R.layout.fragment_notifications) {

    lateinit var binding: FragmentNotificationsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(layoutInflater)



        return binding.root
    }



}