package com.example.attempseven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.attempseven.R
import com.example.attempseven.databinding.FragmentNotificationsBinding


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