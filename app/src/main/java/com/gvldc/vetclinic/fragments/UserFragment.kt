package com.gvldc.vetclinic.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.FirebaseAuth
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.activities.LoginActivity
import com.gvldc.vetclinic.databinding.FragmentUserBinding
import com.gvldc.vetclinic.models.User
import com.gvldc.vetclinic.viewmodels.ViewModel

class UserFragment : Fragment(R.layout.fragment_user){

    private lateinit var bindingFragmentUser: FragmentUserBinding
    private lateinit var auth: FirebaseAuth
    private val viewModel by activityViewModels<ViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingFragmentUser = FragmentUserBinding.inflate(layoutInflater)

        bindingFragmentUser.apply {


            FirebaseAuth.getInstance().currentUser?.let {
                viewModel.getUserData(it.uid.toString()){ user ->
                    if (user != null) {
                        textViewUserName.text = user.name
                        textViewUserPhone.text = "Номер телефона: ${user.phone}"
                        textViewUserEmail.text = "Почта: ${user.email}"
                    }
                }
            }


            buttonSignOut.setOnClickListener {
                signOut()
            }
        }

        return bindingFragmentUser.root
    }



    private fun signOut() {
        auth.signOut()
        navigateToLoginScreen()
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}