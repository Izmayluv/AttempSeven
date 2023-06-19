package com.gvldc.vetclinic.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.databinding.ActivityLoginBinding
import com.gvldc.vetclinic.utils.MyUtils
import java.util.concurrent.TimeUnit

class LoginActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {



            buttonLogin.setOnClickListener {

                val email = editTextEmailAddress.text.toString().trim()
                val password = editTextPassword.text.toString().trim()

                if (email.isEmpty() || password.isEmpty()) {
                    val message = "Введите данные"
                    Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
                } else {
                    signInWithEmailAndPassword(email, password)
                }
            }

            textViewRegistration.setOnClickListener {
                navigateToRegistrationScreen()
            }

            textViewRecovery.setOnClickListener {
                val email = editTextEmailAddress.text.toString().trim()

                if (email.isEmpty()) {
                    val message = "Введите почту"
                    Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
                } else {
                    sendPasswordRecoveryEmail(email)
                }
            }
        }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Вход выполнен успешно
                    navigateToMainScreen()
                } else {
                    // Вход не выполнен, обработка ошибки
                    val message = task.exception?.message ?: "Ошибка входа"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun sendPasswordRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val message = "Письмо восстановления отправлено\n на почту $email"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                } else {
                    // Вход не выполнен, обработка ошибки
                    val message = task.exception?.message ?: "Ошибка входа"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Письмо с подтверждением отправлено
                    Toast.makeText(this, "Письмо с подтверждением отправлено", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    // Ошибка отправки письма с подтверждением
                    Toast.makeText(
                        this,
                        "Ошибка отправки письма с подтверждением",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToRegistrationScreen() {
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
    }
}