package com.gvldc.vetclinic.activities

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Инициализация MapKit
        MapKitFactory.setApiKey("2e3ccb1e-23b2-4242-bdf3-f879bccfea7e")
    }

}
