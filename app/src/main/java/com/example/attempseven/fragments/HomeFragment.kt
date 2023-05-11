package com.example.attempseven.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.attempseven.R
import com.example.attempseven.adapters.GridItemDecoration
import com.example.attempseven.adapters.HomePageAdapter
import com.example.attempseven.models.DataModel
import com.example.attempseven.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    private val inputList = mutableListOf(
        DataModel.ItemHeader(
            "Добро пожаловать"
        ),
        DataModel.ItemHomeStart(
            "qwe",
            "https://animal-doc.ru/images/logo/logo.svg",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
        DataModel.ItemHomeStart(
            "qwe",
            "https://docs.google.com/uc?id=1cefeiDRcJrSAB1zY5MZfrqpAI32MPVjy",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
        DataModel.ItemHomeStart(
            "qwe",
            "https://docs.google.com/uc?id=1cefeiDRcJrSAB1zY5MZfrqpAI32MPVjy",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
        DataModel.ItemHomeStart(
            "qwe",
            "https://docs.google.com/uc?id=1cefeiDRcJrSAB1zY5MZfrqpAI32MPVjy",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
        DataModel.ItemHeader(
            "Акции"
        ),
        DataModel.ItemHomeStart(
            "qwe",
            "https://docs.google.com/uc?id=1cefeiDRcJrSAB1zY5MZfrqpAI32MPVjy",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),
        DataModel.ItemHeader(
            "Новости"
        ),
        DataModel.ItemHomeStart(
            "qwe",
            "https://docs.google.com/uc?id=1cefeiDRcJrSAB1zY5MZfrqpAI32MPVjy",
            "https://docs.google.com/uc?id=1DGOWCWQ763j2uL0PxLnH-AZjB2oaf7pa",
            "https://docs.google.com/uc?id=1IYeOUe4xP_uiiWjoPf_dEAwFTIEm-uEk",
            "https://docs.google.com/uc?id=1MzMeyCwOyjjwijP-v714L7gF8fFZqhWS"
        ),

    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val spacingInDp = 8 // задаем spacing в dp для addItemDecoration
        val density = resources.displayMetrics.density // получаем плотность экрана
        val spacingInPixels = (spacingInDp * density).toInt() // переводим в пиксели
        val adapter = HomePageAdapter()
        adapter.setData(inputList)

        val customLayoutManager = GridLayoutManager(this@HomeFragment.context, 2)
        customLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // First item in the list spans both columns, others only span one column
                return when(position){
                    1 -> 1
                    2 -> 1
                    3 -> 1
                    4 -> 1
                    else -> 2
                }
            }
        }

        binding.rvHome.apply {
            /*layoutManager = LinearLayoutManager(this@HomeFragment.context)
            hasFixedSize()
            this.adapter = adapter*/
            layoutManager = customLayoutManager
            this.adapter = adapter //1
            addItemDecoration(GridItemDecoration(2, spacingInPixels, true))
        }

        return binding.root
    }

}