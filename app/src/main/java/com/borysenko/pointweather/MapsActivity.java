package com.borysenko.pointweather;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String selectedLongitude;
    String selectedLatitude;
    DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng kyiv = new LatLng(50.45, 30.52);
        mMap.addMarker(new MarkerOptions().position(kyiv).title("Kyiv"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kyiv));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);
                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.addMarker(markerOptions);
                selectedLongitude = String.valueOf(df.format(latLng.longitude));
                selectedLatitude = String.valueOf(df.format(latLng.latitude));
            }
        });
    }

    @OnClick(R.id.weather_forecast)
    public void weatherForecastButtonClicked() {
        Intent intent = new Intent(this, ForecastActivity.class);
        intent.putExtra("EXTRA_LONGITUDE", selectedLongitude);
        intent.putExtra("EXTRA_LATITUDE", selectedLatitude);
        startActivity(intent);
    }
}
