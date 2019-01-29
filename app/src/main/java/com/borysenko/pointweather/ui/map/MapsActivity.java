package com.borysenko.pointweather.ui.map;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.borysenko.pointweather.R;
import com.borysenko.pointweather.dagger.DaggerMapsScreenComponent;
import com.borysenko.pointweather.dagger.MapsScreenModule;
import com.borysenko.pointweather.ui.forecast.ForecastActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

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
//        findSpot();
        Intent intent = new Intent(this, ForecastActivity.class);
        intent.putExtra("EXTRA_LONGITUDE", selectedLongitude);
        intent.putExtra("EXTRA_LATITUDE", selectedLatitude);
        startActivity(intent);
    }


    void findSpot() {
        Geocoder geoCoder = new Geocoder(this, Locale.ENGLISH);
        try
        {
            String adderess = "Kiev";
            List<Address> addresses = geoCoder.getFromLocationName(adderess, 5);
            Log.e("first", String.valueOf(addresses.get(0).getLatitude()));
            if (addresses.size() > 0)
            {
                Double lat = (double) (addresses.get(0).getLatitude());
                Double lon = (double) (addresses.get(0).getLongitude());

                Log.e("lat-long", "" + lat + "......." + lon);
                final LatLng user = new LatLng(lat, lon);
                /*used marker for show the location */
                Marker hamburg = mMap.addMarker(new MarkerOptions()
                        .position(user)
                        .title(adderess)
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.common_full_open_on_phone)));
                // Move the camera instantly to hamburg with a zoom of 15.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 15));

                // Zoom in, animating the camera.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
            }
        }
        catch (IOException e)
        {
            Log.e("error", "error");
            e.printStackTrace();
        }
    }
}
