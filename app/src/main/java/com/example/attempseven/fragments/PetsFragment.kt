package com.example.attempseven.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attempseven.R
import com.example.attempseven.activities.MainActivity
import com.example.attempseven.adapters.PetsAdapter
import com.example.attempseven.models.RecyclerViewDataModels
import com.example.attempseven.databinding.FragmentPetsBinding
import com.example.attempseven.interfaces.RecyclerViewItemClickListener
import com.example.attempseven.vm.ViewModel

class PetsFragment : Fragment(R.layout.fragment_pets), RecyclerViewItemClickListener {

    private lateinit var bindingFragmentPets: FragmentPetsBinding
    private val viewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentPets = FragmentPetsBinding.inflate(layoutInflater)
        val adapter = PetsAdapter(this)

        viewModel.getPetsData { data ->
            adapter.setData(data)
        }

        bindingFragmentPets.rvPets.apply {
            layoutManager = LinearLayoutManager(this@PetsFragment.context)
            hasFixedSize()
            this.adapter = adapter
        }

        return bindingFragmentPets.root
    }

    override fun onRecyclerViewItemClick(position: Int) {
        val pet = viewModel.inputListPets[position] as RecyclerViewDataModels.ItemPet
        viewModel.sharedPet = pet

        val selectedPetFragment = SelectedPetFragment()

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, selectedPetFragment)
            .addToBackStack(null)
            .commit()
    }
}