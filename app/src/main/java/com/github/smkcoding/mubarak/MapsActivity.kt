package com.github.smkcoding.mubarak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(),OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this@MapsActivity)
    }

    override fun onMapReady(p0: GoogleMap?) {
        val sydney = LatLng(-8.075741, 112.640941)
        p0 ?: return
        with(p0) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 256F))
            addMarker(MarkerOptions().position(sydney))
        }
    }
}
