package com.example.attempseven.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attempseven.R
import com.example.attempseven.adapters.PetAdapter
import com.example.attempseven.data.PetAdapterDataModel
import com.example.attempseven.databinding.FragmentHomeBinding
import com.example.attempseven.databinding.FragmentPetsBinding
import java.util.Date

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val inputList = mutableListOf(
        ""
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

/*        val adapter = PetAdapter()

        adapter.setData(inputList)
        binding.rvPets.apply {
            layoutManager = LinearLayoutManager(this@PetsFragment.context)
            hasFixedSize()
            this.adapter = adapter
        }*/

        return binding.root
    }

}