package com.gvldc.vetclinic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.adapters.VetsInfoAdapter
import com.gvldc.vetclinic.databinding.FragmentVetsInfoBinding
import com.gvldc.vetclinic.viewmodels.ViewModel


class VetsInfoFragment : Fragment(R.layout.fragment_vets_info) {

    private lateinit var bindingVetsInfo: FragmentVetsInfoBinding
    private val viewModel by activityViewModels<ViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.hideBottomNavMenu(requireActivity())

        bindingVetsInfo = FragmentVetsInfoBinding.inflate(layoutInflater)
        val adapter = VetsInfoAdapter()

        bindingVetsInfo.rvVets.apply {
            layoutManager = LinearLayoutManager(this@VetsInfoFragment.context)
            hasFixedSize()
            this.adapter = adapter
        }

        viewModel.getVetsInfoData { data ->
            adapter.setData(data)
        }

        return bindingVetsInfo.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.showBottomNavMenu(requireActivity())
    }
}