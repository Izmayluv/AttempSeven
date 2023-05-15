package com.example.attempseven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attempseven.R
import com.example.attempseven.adapters.ServicesAdapter
import com.example.attempseven.databinding.FragmentChooseServiceBinding
import com.example.attempseven.interfaces.RecyclerViewItemClickListener
import com.example.attempseven.vm.ViewModel

class ChooseServiceFragment : Fragment(R.layout.fragment_choose_service), RecyclerViewItemClickListener {

    lateinit var bindingFragmentChooseService: FragmentChooseServiceBinding
    private val viewModel by activityViewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentChooseService = FragmentChooseServiceBinding.inflate(layoutInflater)
        val adapter = ServicesAdapter(this)

        viewModel.getServicesData { data ->
            adapter.setData(data)
        }

        bindingFragmentChooseService.rvChooseService.apply {
            layoutManager = LinearLayoutManager(this@ChooseServiceFragment.context)
            hasFixedSize()
            this.adapter = adapter
        }

        return bindingFragmentChooseService.root
    }

    override fun onRecyclerViewItemClick(position: Int) {
        Toast.makeText(context, "Услуга!", Toast.LENGTH_SHORT).show()
    }

}