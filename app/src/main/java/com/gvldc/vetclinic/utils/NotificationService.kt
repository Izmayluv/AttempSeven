package com.gvldc.vetclinic.utils

import android.Manifest
import android.app.Application
import android.app.Notification
import android.app.Notification.BADGE_ICON_SMALL
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.activities.MainActivity
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
        //настройка уведомлений
        val notificationBuilder = NotificationCompat.Builder(this, "123")
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setTicker(remoteMessage.notification?.title.toString())
            .setContentTitle(remoteMessage.notification?.title.toString())
            .setContentText(remoteMessage.notification?.body.toString())
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setAutoCancel(true)
       // notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        notificationBuilder.setContentIntent(pendingIntent)

        // Отправка уведомления
        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e("NotificationService", "Нужно настроить проверку разрешений на уведомления")
        }
        notificationManager.notify(123, notificationBuilder.build())

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