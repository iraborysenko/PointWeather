package com.borysenko.pointweather.ui.forecast;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.borysenko.pointweather.R;
import com.borysenko.pointweather.dagger.DaggerForecastScreenComponent;
import com.borysenko.pointweather.dagger.ForecastScreenModule;

import java.util.Objects;

import javax.inject.Inject;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 28/01/19
 * Time: 23:01
 */
public class ForecastActivity extends AppCompatActivity implements ForecastScreen.View {

    @Inject
    ForecastPresenter mapsPresenter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_forecast);

        DaggerForecastScreenComponent.builder()
                .forecastScreenModule(new ForecastScreenModule(this))
                .build().inject(this);

        String longitude = getIntent().getStringExtra("EXTRA_LONGITUDE");
        String latitude = getIntent().getStringExtra("EXTRA_LATITUDE");
    }
}
