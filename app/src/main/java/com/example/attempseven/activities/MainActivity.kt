package com.example.attempseven.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.attempseven.R
import com.example.attempseven.databinding.ActivityMainBinding
import com.example.attempseven.fragments.PetsFragment
import com.example.attempseven.fragments.HomeFragment
import com.example.attempseven.fragments.MapsFragment
import com.example.attempseven.fragments.UserFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val petsFragment = PetsFragment()
        val homeFragment = HomeFragment()
        val userFragment = UserFragment()
        val mapsFragment = MapsFragment()
        //val newsFeedFragment = NewsFeedFragment()

        setCurrentFragment(homeFragment)
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
/*                R.id.miNewsFeed -> {
                    setCurrentFragment(newsFeedFragment)
                    true
                }*/
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