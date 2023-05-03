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
import com.example.attempseven.databinding.FragmentPetsBinding
import java.util.Date

class PetsFragment : Fragment(R.layout.fragment_pets) {

    private lateinit var binding: FragmentPetsBinding

    private val inputList = mutableListOf(
        PetAdapterDataModel.Header(
            "Мои питомцы "
        ),
        PetAdapterDataModel.Pet(
            "Mickwe",
            "https://pngimg.com/d/cat_PNG50426.png",
            Date(213),
            "Cat|Husewd"
        ),
        PetAdapterDataModel.Pet(
            "Mickwe",
            "https://www.freepnglogos.com/uploads/cat-png/cute-cat-images-download-7.png",
            Date(213),
            "Cat|Husewd"
        ),
        PetAdapterDataModel.Pet(
            "Mickwe",
            "https://pngfre.com/wp-content/uploads/transparent-cat-by-pngfre-75.png",
            Date(213),
            "Cat|Husewd"
        ),
        PetAdapterDataModel.Pet(
            "Mickwe",
            "https://www.freepnglogos.com/uploads/cat-png/cat-sitting-boarding-daycare-15.png",
            Date(213),
            "Cat|Husewd"
        ),
        PetAdapterDataModel.Header(
            "Заголовооок "
        ),
        PetAdapterDataModel.Pet(
            "Mickwe",
            "https://www.freepnglogos.com/uploads/cat-png/cat-sitting-boarding-daycare-15.png",
            Date(213),
            "Cat|Husewd"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPetsBinding.inflate(layoutInflater)

        val adapter = PetAdapter()

        adapter.setData(inputList)
        binding.rvPets.apply {
            layoutManager = LinearLayoutManager(this@PetsFragment.context)
            hasFixedSize()
            this.adapter = adapter
        }

        return binding.root
    }
}