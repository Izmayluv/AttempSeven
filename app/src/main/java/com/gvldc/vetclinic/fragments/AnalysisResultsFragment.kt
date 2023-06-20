package com.gvldc.vetclinic.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.databinding.FragmentAnalysisResultsBinding

class AnalysisResultsFragment: Fragment() {

    lateinit var bindingAnalysisResults: FragmentAnalysisResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return bindingAnalysisResults.root
    }


}