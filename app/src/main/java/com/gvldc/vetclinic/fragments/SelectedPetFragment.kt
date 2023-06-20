package com.gvldc.vetclinic.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.adapters.PetsAdapter
import com.gvldc.vetclinic.adapters.SharedAdapter
import com.gvldc.vetclinic.databinding.FragmentSelectedPetBinding
import com.gvldc.vetclinic.models.RVDataModels
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

        val name = "${pet?.name}"
        val species = "${pet?.species}"
        val birthDay = "${pet?.birthDay}"

        val adapter = SharedAdapter()

        /*viewModel.petsData.observe(viewLifecycleOwner) { data ->
            adapter.setData(data.toMutableList())
        }
        viewModel.fetchPetsData(FirebaseAuth.getInstance().currentUser?.uid.toString())*/

        binding.apply {

            rvAppointments.apply {
                layoutManager = LinearLayoutManager(this@SelectedPetFragment.context)
                hasFixedSize()
                this.adapter = adapter

                adapter.setData(
                    mutableListOf(
                        RVDataModels.ItemHeader("Записи на приём"),
                        RVDataModels.ItemFutureAppointment(
                            "Первичный осмотр", "Воробьев Павел Владимирович",
                            "на ул. Турку", "21.06.2023 10:00", "17.06.2023"
                        ),
                        RVDataModels.ItemFutureAppointment(
                            "УЗИ сердца", "Некрасов Александр Анатольевич",
                            "на ул. Турку", "25.06.2023 13:10", "10.06.2023"
                        )
                    )
                )
        }

            tvName.text = name
            tvSpecies.text = species
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