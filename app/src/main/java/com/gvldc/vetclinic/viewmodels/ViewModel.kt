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
import com.gvldc.vetclinic.models.RecyclerViewDataModels
import com.gvldc.vetclinic.models.User
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
    private var uid: String? = null
    val petsData: LiveData<List<RecyclerViewDataModels>>
        get() = _petsData

    fun fetchPetsData(userId: String) {
        viewModelScope.launch {
            val data = repository.getPetsData(userId)
            _petsData.value = data
        }
    }

    private val userData = MutableLiveData<User>()

/*    fun getUserData(userId: String) {
        repository.getUserData(userId) { user ->
            userData.value = user
        }
    }*/

    // Другие функции и переменные вашего ViewModel

    // Добавьте getter для LiveData userData, если вам нужно получить доступ к данным пользователя из фрагмента или активности
    fun getUserLiveData(): LiveData<User> = userData

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

    fun getCurrentUID() {
        uid
    }

    fun setCurrentUser(userId: String) {
        uid = userId
    }


    fun registerUser(userId: String, name: String, email: String, phone: String) {
        repository.registerUser(userId, name, email, phone)
    }

    fun getHomeData(callback: (MutableList<RecyclerViewDataModels>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getHomeData()
            callback(data)
        }
    }

    fun getServicesData(callback: (MutableList<RecyclerViewDataModels>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getServicesData()
            callback(data)
        }
    }

    fun getNotificationsData(callback: (MutableList<RecyclerViewDataModels>) -> Unit) {
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