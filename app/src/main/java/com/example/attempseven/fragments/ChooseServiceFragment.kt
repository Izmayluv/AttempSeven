package com.example.attempseven.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.attempseven.R
import com.example.attempseven.databinding.FragmentChooseServiceBinding

class ChooseServiceFragment : Fragment(R.layout.fragment_choose_service) {
    lateinit var bindingFragmentRegisterWithDoctor: FragmentChooseServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentRegisterWithDoctor = FragmentChooseServiceBinding.inflate(layoutInflater)

        return bindingFragmentRegisterWithDoctor.root
    }

}