package com.gvldc.vetclinic.utils

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.gvldc.vetclinic.data.Repository
import com.gvldc.vetclinic.models.RVDataModels
import com.gvldc.vetclinic.viewmodels.ViewModel

class NotificationService : FirebaseMessagingService() {


    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        Log.e(TAG, "NewToken->$newToken")
    }

    private val repository = Repository()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        repository.createNotification(
            FirebaseAuth.getInstance().currentUser?.uid.toString(),
            remoteMessage.messageId.toString(),
            remoteMessage.notification?.title.toString(),
            MyUtils.convertLongToTimestamp(remoteMessage.sentTime),
            remoteMessage.notification?.body.toString()
        )
    }


    companion object {
        const val TAG = "MyFirebaseMessagingService"

    }
}