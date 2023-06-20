package com.gvldc.vetclinic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gvldc.vetclinic.databinding.FragmentMedicalHystoryBinding

class MedicalHistoryFragment : Fragment() {

    lateinit var bindingMedicalHistory: FragmentMedicalHystoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindingMedicalHistory.root
    }


}