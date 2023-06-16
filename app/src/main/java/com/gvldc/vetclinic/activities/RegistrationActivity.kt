package com.gvldc.vetclinic.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.databinding.ActivityLoginBinding
import com.gvldc.vetclinic.databinding.ActivityRegistrationBinding
import com.gvldc.vetclinic.utils.MyUtils
import com.gvldc.vetclinic.viewmodels.ViewModel

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val auth = FirebaseAuth.getInstance()

    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        auth.setLanguageCode("ru")

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            buttonRegistration.setOnClickListener {
                val email = editTextEmailAddress.text.toString().trim()
                val password = editTextPassword.text.toString().trim()
                val passwordCheck = editTextCheckPassword.text.toString().trim()
                val name = editTextName.text.toString().trim()
                val phone = editTextPhone.text.toString().trim()

                if (email.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty() || (password != passwordCheck)) {
                    val message = "Введите данные"
                    Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
                } else {
                    registerWithEmailAndPassword(email, password, name, phone)

                }
            }
        }
    }

    private fun registerWithEmailAndPassword(
        email: String,
        password: String,
        userName: String,
        phone: String
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Регистрация выполнена успешно
                    viewModel.registerUser(auth.currentUser?.uid.toString(), userName, email, phone)
                    navigateToMainScreen()
                } else {
                    // Регистрация не выполнена, обработка ошибки
                    val message = task.exception?.message ?: "Ошибка регистрации"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}