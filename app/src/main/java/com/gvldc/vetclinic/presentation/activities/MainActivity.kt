package com.gvldc.vetclinic.presentation.activities

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.databinding.ActivityMainBinding
import com.gvldc.vetclinic.presentation.fragments.ChooseServiceFragment
import com.gvldc.vetclinic.presentation.fragments.PetsFragment
import com.gvldc.vetclinic.presentation.fragments.HomeFragment
import com.gvldc.vetclinic.presentation.fragments.MapsFragment
import com.gvldc.vetclinic.presentation.fragments.NotificationsFragment
import com.gvldc.vetclinic.presentation.fragments.UserFragment
import com.gvldc.vetclinic.domain.viewmodels.ViewModel
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {

    private val petsFragment = PetsFragment()
    private val homeFragment = HomeFragment()
    private val userFragment = UserFragment()
    private val notificationsFragment = NotificationsFragment()
    private val mapsFragment = MapsFragment()
    private val chooseServiceFragment = ChooseServiceFragment()

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация Firebase
        FirebaseApp.initializeApp(this)
        val auth = FirebaseAuth.getInstance()

        // Проверка авторизации пользователя
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // Пользователь не авторизован, перейдите к экрану входа/регистрации
            navigateToLoginScreen(auth)
        }

        MapKitFactory.setApiKey(R.string.yandexApiKey.toString())

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

    private fun navigateToLoginScreen(auth: FirebaseAuth) {
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
                R.id.miMap -> {
                    setCurrentFragment(mapsFragment)
                    true
                }

/*                R.id.miMap -> {
                    setCurrentFragment(chooseServiceFragment)
                    true
                }*/

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