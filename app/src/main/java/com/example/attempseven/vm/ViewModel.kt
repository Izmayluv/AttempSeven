package com.example.attempseven.vm

import android.app.Activity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.attempseven.R
import com.example.attempseven.data.Repository
import com.example.attempseven.models.RecyclerViewDataModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val repository = Repository()
    val inputListPets: MutableList<RecyclerViewDataModels>
        get() = repository.inputListPets
    fun getPetsData(callback: (MutableList<RecyclerViewDataModels>) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getPetsData()
            callback(data)
        }
    }

    fun getHomeData(callback: (MutableList<RecyclerViewDataModels>) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getHomeData()
            callback(data)
        }
    }

    fun getServicesData(callback: (MutableList<RecyclerViewDataModels>) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getServicesData()
            callback(data)
        }
    }

    var sharedPet: RecyclerViewDataModels.ItemPet? = null

    private val _isBottomNavMenuVisible = MutableLiveData<Boolean>(true)
    val isBottomNavMenuVisible: LiveData<Boolean>
        get() = _isBottomNavMenuVisible

    fun hideBottomNavMenu(activity: Activity) {
        _isBottomNavMenuVisible.value = false
        activity.window.navigationBarColor = ContextCompat
            .getColor(
                activity.applicationContext,
                R.color.background
            )
    }

    fun showBottomNavMenu(activity: Activity) {
        _isBottomNavMenuVisible.value = true
        activity.window.navigationBarColor = ContextCompat
            .getColor(
                activity.applicationContext,
                R.color.navBarColor
            )
    }
}