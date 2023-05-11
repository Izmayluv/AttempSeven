package com.example.attempseven.vm

import android.app.Activity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.attempseven.R
import com.example.attempseven.models.DataModel

class ViewModel : ViewModel() {
    var sharedPet: DataModel.ItemPet? = null

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
                R.color.colorSecondary
            )
    }
}