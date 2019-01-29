package com.borysenko.pointweather.ui.forecast;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.borysenko.pointweather.R;
import com.borysenko.pointweather.adapters.WeatherRecyclerAdapter;
import com.borysenko.pointweather.dagger.DaggerForecastScreenComponent;
import com.borysenko.pointweather.dagger.ForecastScreenModule;
import com.borysenko.pointweather.dagger.NetModule;
import com.borysenko.pointweather.model.WeatherItem;

import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 28/01/19
 * Time: 23:01
 */
public class ForecastActivity extends AppCompatActivity implements ForecastScreen.View {

    @Inject
    ForecastPresenter forecastPresenter;

    @BindView(R.id.loading_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.city)
    TextView mCity;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_forecast);
        ButterKnife.bind(this);

        DaggerForecastScreenComponent.builder()
                .forecastScreenModule(new ForecastScreenModule(this))
                .netModule(new NetModule())
                .build().inject(this);

        String longitude = getIntent().getStringExtra("EXTRA_LONGITUDE");
        String latitude = getIntent().getStringExtra("EXTRA_LATITUDE");

        forecastPresenter.loadForecastFor5Days(longitude, latitude);
    }

    @Override
    public void initRecyclerView(WeatherItem[] weatherItems) {
        final RecyclerView mRecyclerView = findViewById(R.id.weather_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final WeatherRecyclerAdapter mAdapter =
                new WeatherRecyclerAdapter(weatherItems, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setCityData(String cityName, String cityCountry) {
        Locale obj = new Locale("", cityCountry);
        mCity.setText(String.format("%s, %s", cityName, obj.getDisplayCountry(Locale.ENGLISH)));
    }

    @Override
    public void setProgressBarVisible() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProgressBarInvisible() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
