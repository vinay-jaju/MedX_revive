package com.example.android.medx;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mCurrentLocation;
    double lat, longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getSystemService(Context.LOCATION_SERVICE);
        mCurrentLocation=locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        lat=mCurrentLocation.getLatitude();
        longi=mCurrentLocation.getLongitude();

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
        CameraPosition position = CameraPosition.builder()
                .target( new LatLng( lat,
                        longi ) )
                .zoom( 16f )
                .bearing( 0.0f )
                .tilt( 0.0f )
                .build();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(lat, longi);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Your location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
         googleMap.setMyLocationEnabled(true);
          googleMap.getUiSettings().setZoomControlsEnabled(true);
          googleMap.getUiSettings().setCompassEnabled(true);
          googleMap.getUiSettings().setMyLocationButtonEnabled(true);
          googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
      googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), null);

    }
}
