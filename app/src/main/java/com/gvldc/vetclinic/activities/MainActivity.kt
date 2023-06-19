package com.gvldc.vetclinic.activities

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.databinding.ActivityMainBinding
import com.gvldc.vetclinic.fragments.ChooseServiceFragment
import com.gvldc.vetclinic.fragments.PetsFragment
import com.gvldc.vetclinic.fragments.HomeFragment
import com.gvldc.vetclinic.fragments.MapsFragment
import com.gvldc.vetclinic.fragments.NotificationsFragment
import com.gvldc.vetclinic.fragments.UserFragment
import com.gvldc.vetclinic.utils.NotificationService
import com.gvldc.vetclinic.viewmodels.ViewModel
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {

    private val petsFragment = PetsFragment()
    private val homeFragment = HomeFragment()
    private val userFragment = UserFragment()
    private val notificationsFragment = NotificationsFragment()
    private val mapsFragment = MapsFragment()
    private val chooseServiceFragment = ChooseServiceFragment()

    private lateinit var pushBroadcastReceiver: BroadcastReceiver
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val messaging = FirebaseMessaging.getInstance()

        messaging.token.addOnCompleteListener(){task ->
            if (!task.isSuccessful){
                return@addOnCompleteListener
            }
            val token = task.result
            Log.d(NotificationService.TAG, "Token: $token")
        }

/*        messaging.subscribeToTopic("vetclinic")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Подписка выполнена успешно
                    Log.d(NotificationService.TAG, "Подписка на тему выполнена успешно")
                } else {
                    // Ошибка подписки
                    Log.e(NotificationService.TAG, "Ошибка подписки на тему", task.exception)
                }
            }*/



        // Инициализация Firebase
        FirebaseApp.initializeApp(this)
        val auth = FirebaseAuth.getInstance()

        // Проверка авторизации пользователя
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // Пользователь не авторизован, перейдите к экрану входа/регистрации
            navigateToLoginScreen()
        }

       // viewModel.showBottomNavMenu(Activity())

        MapKitFactory.setApiKey("2e3ccb1e-23b2-4242-bdf3-f879bccfea7e")

        setColorStateListForBottomNavMenu(
            activeColorId =  R.color.itemMenuActive,
            inactiveColorId = R.color.itemMenuInactive
        )

        switchingFragments()
        setCurrentFragment(fragment = homeFragment)

        viewModel.isBottomNavMenuVisible.observe(this) { isVisible ->
            binding.apply {
                bottomNavigationView.visibility =
                    if (isVisible) View.VISIBLE
                    else View.GONE
            }
        }
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setColorStateListForBottomNavMenu(activeColorId:Int, inactiveColorId:Int){
        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked), // checked
            intArrayOf(-android.R.attr.state_checked) // unchecked
        )
        val active = ContextCompat.getColor(this, activeColorId)
        val inactive = ContextCompat.getColor(this, inactiveColorId)

        val colors = intArrayOf(
            active, // checked color
            inactive // unchecked color
        )
        val stateList = ColorStateList(states, colors)
        binding.bottomNavigationView.itemIconTintList = stateList
    }

    private fun switchingFragments(){
        binding.bottomNavigationView.selectedItemId = R.id.miHome

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.miHome -> {
                    setCurrentFragment(homeFragment)
                    true
                }
                R.id.miPets -> {
                    setCurrentFragment(petsFragment)
                    true
                }
                R.id.miUser -> {
                    setCurrentFragment(userFragment)
                    true
                }
                R.id.miNotifications -> {
                    setCurrentFragment(notificationsFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}