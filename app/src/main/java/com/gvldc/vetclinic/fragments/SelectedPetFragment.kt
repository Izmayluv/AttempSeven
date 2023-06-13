package com.gvldc.vetclinic.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.databinding.FragmentSelectedPetBinding
import com.gvldc.vetclinic.viewmodels.ViewModel

class SelectedPetFragment : Fragment(R.layout.fragment_selected_pet) {

    private lateinit var binding: FragmentSelectedPetBinding
    private val viewModel by activityViewModels<ViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.hideBottomNavMenu(requireActivity())
        binding = FragmentSelectedPetBinding.inflate(layoutInflater)

        val pet = viewModel.sharedPet

        val name = "${pet?.name}\n"
        val breed = "${pet?.breed}\n"
        val birthDay = MyUtils.dateToString(pet?.birthDay)

        binding.apply {

            tvName.text = name
            tvBreed.text = breed
            tvAge.text = birthDay

            MyUtils.loadImageFromUrl(
                pet?.imageUrl,
                iv
            )
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.showBottomNavMenu(requireActivity())
    }
}