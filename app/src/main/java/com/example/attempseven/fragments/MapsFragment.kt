package com.example.attempseven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.attempseven.MyUtils
import com.example.attempseven.R
import com.example.attempseven.databinding.FragmentMapsBinding


class MapsFragment : Fragment(R.layout.fragment_maps) {

    private lateinit var binding: FragmentMapsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(layoutInflater)
        MyUtils.loadImageFromUrl(
            "https://www.freepnglogos.com/uploads/cat-png/cat-sitting-boarding-daycare-15.png",
            binding.imageMap
        )
        return binding.root
    }
}