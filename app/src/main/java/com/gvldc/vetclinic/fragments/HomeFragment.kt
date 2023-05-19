package com.gvldc.vetclinic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.adapters.GridItemDecoration
import com.gvldc.vetclinic.adapters.HomePageAdapter
import com.gvldc.vetclinic.databinding.FragmentHomeBinding
import com.gvldc.vetclinic.interfaces.RecyclerViewItemClickListener
import com.gvldc.vetclinic.viewmodels.ViewModel

class HomeFragment : Fragment(R.layout.fragment_home), RecyclerViewItemClickListener {

    private lateinit var bindingFragmentHome: FragmentHomeBinding
    private val viewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentHome = FragmentHomeBinding.inflate(layoutInflater)

        val spacingInDp = 8 // задаем spacing в dp для addItemDecoration
        val density = resources.displayMetrics.density // получаем плотность экрана
        val spacingInPixels = (spacingInDp * density).toInt() // переводим в пиксели

        val adapter = HomePageAdapter(this)
        viewModel.getHomeData { data ->
            adapter.setData(data)
        }

        val customLayoutManager = GridLayoutManager(this@HomeFragment.context, 2)
        customLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                // First item in the list spans both columns, others only span one column
                return when(position){
                    1,2 -> 1
                    3,4 -> 1
                    else -> 2
                }
            }
        }

        bindingFragmentHome.rvHome.apply {
            layoutManager = customLayoutManager
            this.adapter = adapter //1
            addItemDecoration(GridItemDecoration(2, spacingInPixels, true))
        }

        return bindingFragmentHome.root
    }

    override fun onRecyclerViewItemClick(position: Int) {
        Toast.makeText(context, "Запись!", Toast.LENGTH_SHORT).show()
    }
}