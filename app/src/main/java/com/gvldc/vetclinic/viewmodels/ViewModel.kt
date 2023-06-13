package com.gvldc.vetclinic.viewmodels

import android.app.Activity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.data.Repository
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val repository = Repository()

/*    val inputListPets: MutableList<RecyclerViewDataModels>
        get() = repository.inputListPets
    fun getPetsData(callback: (MutableList<RecyclerViewDataModels>) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getPetsData()
            callback(data)
        }
    }*/

    private val _petsData = MutableLiveData<List<RecyclerViewDataModels>>()
    val petsData: LiveData<List<RecyclerViewDataModels>>
        get() = _petsData

    fun fetchPetsData() {
        viewModelScope.launch {
            val data = repository.getPetsData()
            _petsData.value = data
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

    fun getNotificationsData(callback: (MutableList<RecyclerViewDataModels>) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getNotificationsData()
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