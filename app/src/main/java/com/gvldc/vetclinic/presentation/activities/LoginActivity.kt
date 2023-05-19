package com.gvldc.vetclinic.presentation.activities

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
import java.util.concurrent.TimeUnit

class LoginActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        auth.setLanguageCode("ru")

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            buttonLogin.setOnClickListener {
                val email = editTextTextEmailAddress.text.toString().trim()
                val password = editTextTextPassword.text.toString().trim()

                if (email.isNullOrEmpty() || password.isNullOrEmpty()){
                    val message = "Введите данные"
                    Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
                }
                else{
                    signInWithEmailAndPassword(email, password)
                }

            }
            buttonRegister.setOnClickListener {
                val email = editTextTextEmailAddress.text.toString().trim()
                val password = editTextTextPassword.text.toString().trim()

                if (email.isNullOrEmpty() || password.isNullOrEmpty()){
                    val message = "Введите данные"
                    Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
                }
                else{
                    registerWithEmailAndPassword(email, password)
                }
            }
/*            buttonRegisterPhone.setOnClickListener {
                val phoneNumber = phoneNumberEditText.text.toString()
                startPhoneVerification(phoneNumber)
            }*/
        }

/*        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // Автоматическое завершение верификации номера телефона
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                // Ошибка верификации номера телефона
                Toast.makeText(
                    this@LoginActivity,
                    "Ошибка верификации номера телефона",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // Код подтверждения отправлен на номер телефона
                // Сохраните verificationId и token в SharedPreferences или переменных класса для использования в следующем шаге
                // Перейдите к экрану для ввода кода подтверждения
                val intent = Intent(this@LoginActivity, PhoneVerificationActivity::class.java)
                intent.putExtra("verificationId", verificationId)
                startActivity(intent)
            }
        }*/


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

    private fun registerWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Регистрация выполнена успешно
                    navigateToMainScreen()
                } else {
                    // Регистрация не выполнена, обработка ошибки
                    val message = task.exception?.message ?: "Ошибка регистрации"
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
    }

/*    private fun startPhoneVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
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
    }*/

    private fun sendEmailVerification() {
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Письмо с подтверждением отправлено
                    Toast.makeText(this, "Письмо с подтверждением отправлено", Toast.LENGTH_SHORT).show()
                } else {
                    // Ошибка отправки письма с подтверждением
                    Toast.makeText(this, "Ошибка отправки письма с подтверждением", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}