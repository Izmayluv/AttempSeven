package com.gvldc.vetclinic.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import com.gvldc.vetclinic.R
import com.gvldc.vetclinic.databinding.FragmentClinicsInfoBinding
import com.gvldc.vetclinic.utils.IntentKeys
import com.gvldc.vetclinic.viewmodels.ViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider


class ClinicsInfoFragment : Fragment(R.layout.fragment_clinics_info) {

    private lateinit var bindingClinicsInfoFragment: FragmentClinicsInfoBinding
    private lateinit var mapView: MapView
    private var targetLocation = Point(59.858406, 30.273496)
    private val viewModel by activityViewModels<ViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.hideBottomNavMenu(requireActivity())

        bindingClinicsInfoFragment = FragmentClinicsInfoBinding.inflate(layoutInflater)
        MapKitFactory.initialize(bindingClinicsInfoFragment.root.context)
        mapView = bindingClinicsInfoFragment.mapViewClinicsInfo
        mapView.map.isRotateGesturesEnabled = false

        val mapKit = MapKitFactory.getInstance()
        mapKit.createUserLocationLayer(mapView.mapWindow).apply {
            isVisible = true
            isHeadingEnabled = true
        }

        val petergofskoe = Point(59.849291, 30.150220)
        val turky = Point(59.847621, 30.386851)
        val dimitrova = Point(59.865798, 30.379967)

        val clinicCoordsList: List<Point> = listOf(
            petergofskoe,
            turky,
            dimitrova
        )

        val mapObjects = mapView.map.mapObjects.addCollection()
        clinicCoordsList.forEach { point ->
            mapObjects.addPlacemark(
                point,
                ImageProvider.fromResource(
                requireContext(),
                R.drawable.img_location_icon48
            ))
        }

        mapView.map.move(
            CameraPosition(targetLocation, 11.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 1F),
            null
        )

        bindingClinicsInfoFragment.textViewClinicPhone.setOnClickListener {
            navigateToCallOrRequestPerms()
            //Toast.makeText(bindingClinicsInfoFragment.root.context, "TEXT", Toast.LENGTH_SHORT).show()
        }

        return bindingClinicsInfoFragment.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.showBottomNavMenu(requireActivity())
    }

    private fun navigateToCallOrRequestPerms() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            dialPhoneNumber()
        } else {
            requestCallPhonePermission()
        }
    }

    private fun dialPhoneNumber() {
        val intent = Intent(Intent.ACTION_DIAL)
        val phoneNumber = "88126607780"
        intent.data = Uri.parse("tel:$phoneNumber")
        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun requestCallPhonePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.CALL_PHONE
            )
        ) {
            // Показать объяснение пользователю, почему требуется разрешение

            // Затем запросить разрешение
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                IntentKeys.CALL_PHONE_PERMISSION_REQUEST_CODE
            )
        } else {
            // Запросить разрешение без объяснения
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                IntentKeys.CALL_PHONE_PERMISSION_REQUEST_CODE
            )
        }
    }
}