package com.borysenko.pointweather.ui.map;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.borysenko.pointweather.R;
import com.borysenko.pointweather.dagger.DaggerMapsScreenComponent;
import com.borysenko.pointweather.dagger.MapsScreenModule;
import com.borysenko.pointweather.ui.forecast.ForecastActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsScreen.View {

    private GoogleMap mMap;
    String selectedLongitude;
    String selectedLatitude;
    DecimalFormat df = new DecimalFormat("0.00");

    @Inject
    MapsPresenter mapsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        DaggerMapsScreenComponent.builder()
                .mapsScreenModule(new MapsScreenModule(this))
                .build().inject(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng kyiv = new LatLng(50.45, 30.52);
        selectedLongitude = "30.52";
        selectedLatitude = "50.45";
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
