
package com.example.attempseven.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.attempseven.R
import com.example.attempseven.databinding.FragmentMapsBinding
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.FilteringMode
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.sensors.LocationActivityType


class MapsFragment : Fragment(R.layout.fragment_maps) {

    private lateinit var binding: FragmentMapsBinding
    private lateinit var mapView: MapView
    private var targetLocation = Point(59.945933, 30.320045)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(layoutInflater)
        MapKitFactory.initialize(binding.root.context)
        mapView = binding.mapview
        mapView.map.isRotateGesturesEnabled = false

        val mapKit = MapKitFactory.getInstance()
        mapKit.createUserLocationLayer(mapView.mapWindow).apply {
            isVisible = true
            isHeadingEnabled = true
        }


        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Запросить разрешения у пользователя
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                0
            )
        } else {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
            // Разрешения уже предоставлены, выполнить необходимые действия
            fusedLocationClient.lastLocation.addOnSuccessListener { location: android.location.Location? ->
                if (location != null) {
                    targetLocation = Point(location.latitude, location.longitude)
                    mapView.map.move(
                        CameraPosition(Point(location.latitude, location.longitude), 14.0f, 0.0f, 0.0f),
                        Animation(Animation.Type.SMOOTH, 1F),
                        null
                    )
                }
            }
        }


        mapKit.createLocationManager(LocationActivityType.PEDESTRIAN).subscribeForLocationUpdates(
            0.0,
            0,
            0.0,
            true,
            FilteringMode.ON,
            object : LocationListener {

                override fun onLocationUpdated(location: Location) {
                    targetLocation = location.position
                    mapView.map.move(
                        CameraPosition(location.position, 14.0f, 0.0f, 0.0f),
                        Animation(Animation.Type.SMOOTH, 1F),
                        null
                    )
                }

                override fun onLocationStatusUpdated(locationStatus: LocationStatus) {}
            })

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
}