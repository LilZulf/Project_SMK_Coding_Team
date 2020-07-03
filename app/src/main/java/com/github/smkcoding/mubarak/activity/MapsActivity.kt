package com.github.smkcoding.mubarak.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.smkcoding.mubarak.R
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
        val intentData = intent.extras
        val latitde = intentData!!.getString("latitude")
        val longitude = intentData!!.getString("longitude")
        val sydney = LatLng(latitde!!.toDouble(), longitude!!.toDouble())
        p0 ?: return
        with(p0) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17F))
            addMarker(MarkerOptions().position(sydney))
        }
    }
}
