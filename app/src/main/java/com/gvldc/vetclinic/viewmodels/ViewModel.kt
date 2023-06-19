package com.gvldc.vetclinic.viewmodels

import android.app.Activity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.data.Repository
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel : ViewModel() {

    private val repository = Repository()
    private val _petsData = MutableLiveData<List<RVDataModels>>()
    private var uid: String? = null
    val petsData: LiveData<List<RVDataModels>>
        get() = _petsData

    fun fetchPetsData(userId: String) {
        viewModelScope.launch {
            val data = repository.getPetsData(userId)
            _petsData.value = data
        }
    }

    fun createPet(
        userId: String,
        petId: String,
        name: String,
        birthday: Timestamp,
        imageUrl: String,
        species: String,
        breed: String
    ) {
        repository.createPet(userId, petId, name, birthday, imageUrl, species, breed)
    }

    fun createPetWithoutImage(
        userId: String,
        petId: String,
        name: String,
        birthday: Timestamp,
        species: String,
        breed: String
    ) {
        repository.createPetWithoutImage(userId, petId, name, birthday, species, breed)
    }

    fun getUserData(userId: String, callback: (User?) -> Unit) {
        repository.getUserData(userId) { user ->
            callback(user)
        }
    }

    fun getNotificationsData(userId: String, callback: (MutableList<RVDataModels>) -> Unit) {
        repository.getNotificationsData(userId) {
            callback(it)
        }
    }

    fun registerUser(userId: String, name: String, email: String, phone: String) {
        repository.registerUser(userId, name, email, phone)
    }

    fun getHomeData(callback: (MutableList<RVDataModels>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getHomeData()
            callback(data)
        }
    }

    fun getServicesData(callback: (MutableList<RVDataModels>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getServicesData()
            callback(data)
        }
    }

    fun getVetsInfoData(callback: (MutableList<RVDataModels.VetInfo>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getVetsInfo()
            callback(data)
        }
    }

/*    fun getNotificationsData(callback: (MutableList<RVDataModels.ItemNotification>) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getNotificationsData()
            callback(data)
        }
    }*/



    var sharedPet: RVDataModels.ItemPet? = null

    private val _isBottomNavMenuVisible = MutableLiveData<Boolean>(true)
    val isBottomNavMenuVisible: LiveData<Boolean>
        get() = _isBottomNavMenuVisible

    fun hideBottomNavMenu(activity: Activity) {
        _isBottomNavMenuVisible.value = false
        activity.window.navigationBarColor = ContextCompat
            .getColor(
                activity.applicationContext,
                R.color.transparent
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