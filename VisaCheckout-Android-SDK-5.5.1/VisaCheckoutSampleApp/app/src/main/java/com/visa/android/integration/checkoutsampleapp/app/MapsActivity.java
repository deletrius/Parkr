package com.visa.android.integration.checkoutsampleapp.app;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);

        LatLng marker0 = new LatLng(36.1212, -115.1697);
        LatLng marker1 = new LatLng(36.114460, -115.164);
        LatLng marker2 = new LatLng(36.118196, -115.164039);
        LatLng marker3 = new LatLng(36.111348, -115.170897);

        mMap.addMarker(new MarkerOptions().position(marker0).title("Homer's Parking").snippet("Park in my garage for $8/day.").icon(BitmapDescriptorFactory.fromResource(R.drawable.eight)));
        mMap.addMarker(new MarkerOptions().position(marker1).title("Amy's Parking").snippet("Park in my garage for $1/day.").icon(BitmapDescriptorFactory.fromResource(R.drawable.one)));
        mMap.addMarker(new MarkerOptions().position(marker2).title("Jamie's Parking").snippet("Park in my garage for $8/day.").icon(BitmapDescriptorFactory.fromResource(R.drawable.eight)));
        mMap.addMarker(new MarkerOptions().position(marker3).title("Oprah's Parking").snippet("Park in my garage for $5/day.").icon(BitmapDescriptorFactory.fromResource(R.drawable.five)));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker0, 14));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
}
