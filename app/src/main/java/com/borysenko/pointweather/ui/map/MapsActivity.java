package com.borysenko.pointweather.ui.map;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.borysenko.pointweather.R;
import com.borysenko.pointweather.dagger.screens.DaggerMapsScreenComponent;
import com.borysenko.pointweather.dagger.screens.MapsScreenModule;
import com.borysenko.pointweather.ui.forecast.ForecastActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsScreen.View {

    private GoogleMap mMap;
    String selectedLongitude;
    String selectedLatitude;
    DecimalFormat df = new DecimalFormat("0.0");
    //using "0.0" it's easier to match at the same point in offline mode

    @Inject
    MapsPresenter mapsPresenter;

    @BindView(R.id.search_query)
    EditText queryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        DaggerMapsScreenComponent.builder()
                .mapsScreenModule(new MapsScreenModule(this))
                .build().inject(this);

        queryText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    mapsPresenter.getEnteredData(queryText.getText().toString());
                    return true;
                }
                return false;
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Double defaultLon = 30.52;
        Double defaultLat = 50.43;
        LatLng kyiv = new LatLng(defaultLat, defaultLon);
        selectedLongitude = String.valueOf(defaultLon);
        selectedLatitude = String.valueOf(defaultLat);
        mMap.addMarker(new MarkerOptions().position(kyiv));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kyiv, 5));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
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

    @Override
    public void findPlace(String search) {
        // Geocoder works some time after rebooting. At least, at my device.
        // Couldn't find free analogue service to fix it fast enough
        Geocoder geoCoder = new Geocoder(this, Locale.ENGLISH);
        try
        {
            List<Address> place = geoCoder.getFromLocationName(search, 2);
            if (place.size()!=0) {

                Double lat = place.get(0).getLatitude();
                Double lon = place.get(0).getLongitude();
                selectedLongitude = String.valueOf(df.format(place.get(0).getLongitude()));
                selectedLatitude = String.valueOf(df.format(place.get(0).getLatitude()));

                final LatLng user = new LatLng(lat, lon);
                mMap.clear();
                mMap.addMarker(new MarkerOptions()
                        .position(user)
                        .title(search));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 5));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);
            } else showErrorMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            showReloadRequestMessage();
        }
    }

    void showErrorMessage() {
        Toast.makeText(getApplicationContext(),
                "Choose more accurate name", Toast.LENGTH_SHORT).show();
    }

    void showReloadRequestMessage() {
        Toast.makeText(getApplicationContext(),
                "Need to reboot the device", Toast.LENGTH_SHORT).show();
    }
}
