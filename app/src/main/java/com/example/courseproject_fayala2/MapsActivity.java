package com.example.courseproject_fayala2;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.courseproject_fayala2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    public final static String LOCATION = "com.example.courseproject_fayala2.LOCATION";
    String location = "";
    double latitude = 38.829044;
    double longitude = -77.498393;
    List<Address>geocodeMatches = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Intent intent = getIntent();

        //location = intent.getStringExtra(MapsActivity.LOCATION);

        //Toast.makeText(getApplicationContext(), location, Toast.LENGTH_SHORT).show();

        /*
        try {
            geocodeMatches = new Geocoder(this).getFromLocationName(location, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //if (!geocodeMatches.isEmpty()) {
        if (geocodeMatches != null) {
            //latitude = geocodeMatches.get(0).getLatitude();
            //longitude = geocodeMatches.get(0).getLongitude();
        }
        */
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

        // Add a marker in Sydney and move the camera
        LatLng new_location = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(new_location).title(location));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new_location));
    }

    public void onBackPressed() {
        finish();
    }
}