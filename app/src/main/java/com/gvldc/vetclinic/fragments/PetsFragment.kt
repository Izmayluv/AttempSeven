package com.gvldc.vetclinic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.activities.MainActivity
import com.gvldc.vetclinic.adapters.PetsAdapter
import com.gvldc.vetclinic.databinding.FragmentAddPetBinding
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import com.gvldc.vetclinic.databinding.FragmentPetsBinding
import com.gvldc.vetclinic.interfaces.ItemClickListener
import com.gvldc.vetclinic.viewmodels.ViewModel

class PetsFragment : Fragment(R.layout.fragment_pets), ItemClickListener {

    private lateinit var bindingFragmentPets: FragmentPetsBinding
    private lateinit var bindingFragmentAddPet: FragmentAddPetBinding

    private val viewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingFragmentPets = FragmentPetsBinding.inflate(layoutInflater)
        val adapter = PetsAdapter(this)

        viewModel.petsData.observe(viewLifecycleOwner) { data ->
            adapter.setData(data.toMutableList())
        }
        viewModel.fetchPetsData(FirebaseAuth.getInstance().currentUser?.uid.toString())

        bindingFragmentPets.rvPets.apply {
            layoutManager = LinearLayoutManager(this@PetsFragment.context)
            hasFixedSize()
            this.adapter = adapter
        }

        bindingFragmentPets.floatingActionButtonAddPet.setOnClickListener {
            val addPetFragment = AddPetFragment()

            val mainActivity = requireActivity() as MainActivity
            mainActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.flFragment, addPetFragment)
                .addToBackStack(null)
                .commit()
        }


        return bindingFragmentPets.root
    }

    override fun onRecyclerViewItemClick(position: Int) {
        val pet = viewModel.petsData.value?.get(position) as? RecyclerViewDataModels.ItemPet
        viewModel.sharedPet = pet

        val selectedPetFragment = SelectedPetFragment()

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, selectedPetFragment)
            .addToBackStack(null)
            .commit()
    }
}
