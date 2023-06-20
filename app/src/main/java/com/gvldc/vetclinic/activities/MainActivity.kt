package com.gvldc.vetclinic.activities

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.databinding.ActivityMainBinding
import com.gvldc.vetclinic.fragments.PetsFragment
import com.gvldc.vetclinic.fragments.HomeFragment
import com.gvldc.vetclinic.fragments.NotificationsFragment
import com.gvldc.vetclinic.fragments.UserFragment
import com.gvldc.vetclinic.utils.NotificationService
import com.gvldc.vetclinic.viewmodels.ViewModel
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ViewModel>()

    private val petsFragment = PetsFragment()
    private val homeFragment = HomeFragment()
    private val userFragment = UserFragment()
    private val notificationsFragment = NotificationsFragment()

    //private var isMapKitInitialized = false

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initMapKit()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.cancel(123)

        val messaging = FirebaseMessaging.getInstance()

        messaging.token.addOnCompleteListener(){task ->
            if (!task.isSuccessful){
                return@addOnCompleteListener
            }
            val token = task.result
            Log.d(NotificationService.TAG, "Token: $token")
        }

        notificationsProcessing()

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

    /*private fun initMapKit() {
        MapKitFactory.setApiKey("2e3ccb1e-23b2-4242-bdf3-f879bccfea7e")
        MapKitFactory.initialize(this)
    }*/

/*    private fun initMapKit() {
        if (!isMapKitInitialized) {
            MapKitFactory.setApiKey("2e3ccb1e-23b2-4242-bdf3-f879bccfea7e")
            MapKitFactory.initialize(this)
            isMapKitInitialized = true
        }
    }*/

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

    override fun onResume() {
        super.onResume()

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.cancel(123)
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun notificationsProcessing() {
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        val areNotificationsEnabled = notificationManagerCompat.areNotificationsEnabled()
        if (areNotificationsEnabled) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    "123",
                    "Имя канала",
                    NotificationManager.IMPORTANCE_HIGH
                )
                channel.enableLights(true)
                channel.enableVibration(true)

                val notificationManager = getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)
            }
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    123
                )
            }
        }
    }
}