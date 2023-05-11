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
import com.example.attempseven.models.DataModel
import com.example.attempseven.databinding.FragmentPetsBinding
import com.example.attempseven.interfaces.OnItemClickListener
import com.example.attempseven.vm.ViewModel
import java.util.Date

class PetsFragment : Fragment(R.layout.fragment_pets), OnItemClickListener {

    private lateinit var bindingFragmentPetsBinding: FragmentPetsBinding
    private val viewModel by activityViewModels<ViewModel>()

    private val inputList = mutableListOf(
        DataModel.ItemHeader(
            "Мои питомцы "
        ),
        DataModel.ItemPet(
            "Алекс",
            "https://pngimg.com/d/cat_PNG50426.png",
            Date(245),
            "Сиамская",
            "1"
        ),
        DataModel.ItemPet(
            "Шон",
            "https://www.freepnglogos.com/uploads/cat-png/cute-cat-images-download-7.png",
            Date(220),
            "Персидская",
            "2"
        ),
        DataModel.ItemPet(
            "Джерри",
            "https://pngfre.com/wp-content/uploads/transparent-cat-by-pngfre-75.png",
            Date(260),
            "Мейн-кун",
            "3"
        ),
        DataModel.ItemPet(
            "Дейзи",
            "https://www.freepnglogos.com/uploads/cat-png/cat-sitting-boarding-daycare-15.png",
            Date(290),
            "Корниш-рекс",
            "4"
        ),
        DataModel.ItemPet(
            "Ханна",
            "https://docs.google.com/uc?id=1jqKbtJUYtZE5SKuoGSxUHVHvJRymbAF_",
            Date(300),
            "Пикси-боб",
            "5"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentPetsBinding = FragmentPetsBinding.inflate(layoutInflater)
        val adapter = PetsAdapter(this)

        adapter.setData(inputList)
        bindingFragmentPetsBinding.rvPets.apply {
            layoutManager = LinearLayoutManager(this@PetsFragment.context)
            hasFixedSize()
            this.adapter = adapter
        }

        return bindingFragmentPetsBinding.root
    }

    override fun onItemClick(pet: DataModel.ItemPet) {

        viewModel.sharedPet = pet

        val selectedPetFragment = SelectedPetFragment()

        val mainActivity = requireActivity() as MainActivity
        mainActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.flFragment, selectedPetFragment)
            .addToBackStack(null)
            .commit()
    }
}