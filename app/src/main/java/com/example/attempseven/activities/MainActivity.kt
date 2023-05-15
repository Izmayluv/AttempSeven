package com.example.attempseven.activities

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.attempseven.R
import com.example.attempseven.databinding.ActivityMainBinding
import com.example.attempseven.fragments.ChooseServiceFragment
import com.example.attempseven.fragments.PetsFragment
import com.example.attempseven.fragments.HomeFragment
import com.example.attempseven.fragments.MapsFragment
import com.example.attempseven.fragments.NotificationsFragment
import com.example.attempseven.fragments.UserFragment
import com.example.attempseven.vm.ViewModel
import com.yandex.mapkit.MapKitFactory

class MainActivity : AppCompatActivity() {

    companion object{
        val petsFragment = PetsFragment()
        val homeFragment = HomeFragment()
        val userFragment = UserFragment()
        val notificationsFragment = NotificationsFragment()
       // val mapsFragment = MapsFragment()
        val chooseServiceFragment = ChooseServiceFragment()
    }

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
                /*R.id.miMap -> {
                    setCurrentFragment(mapsFragment)
                    true
                }*/

                R.id.miMap -> {
                    setCurrentFragment(chooseServiceFragment)
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