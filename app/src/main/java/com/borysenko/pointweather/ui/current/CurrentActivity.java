package com.borysenko.pointweather.ui.current;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.borysenko.pointweather.R;
import com.borysenko.pointweather.dagger.ContextModule;
import com.borysenko.pointweather.dagger.screens.CurrentScreenModule;
import com.borysenko.pointweather.dagger.screens.DaggerCurrentScreenComponent;
import com.borysenko.pointweather.model.CurrentWeather;
import com.borysenko.pointweather.utils.API;
import com.borysenko.pointweather.utils.GeneralMethods;
import com.borysenko.pointweather.utils.Messages;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 10/02/19
 * Time: 12:01
 */
public class CurrentActivity extends AppCompatActivity implements CurrentScreen.View {

    @Inject
    CurrentPresenter currentPresenter;

    @BindView(R.id.loading_curr_progress_bar) ProgressBar mProgressBar;
    @BindView(R.id.curr_weather_icon) ImageView mCurrWeatherIcon;
    @BindView(R.id.curr_city) TextView mLocation;
    @BindView(R.id.curr_date) TextView mDate;
    @BindView(R.id.curr_temperature) TextView mTemperature;
    @BindView(R.id.curr_description) TextView mDescription;
    @BindView(R.id.curr_wind_speed) TextView mWindSpeed;
    @BindView(R.id.curr_wind_direction) TextView mWindDirection;
    @BindView(R.id.curr_pressure) TextView mPressure;
    @BindView(R.id.curr_humidity) TextView mHumidity;
    @BindView(R.id.curr_cloudiness) TextView mCloudiness;
    @BindView(R.id.curr_rain_volume) TextView mRainVolume;
    @BindView(R.id.sunrise) TextView mSunrise;
    @BindView(R.id.sunset) TextView mSunset;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_current);
        ButterKnife.bind(this);

        DaggerCurrentScreenComponent.builder()
                .currentScreenModule(new CurrentScreenModule(this))
                .contextModule(new ContextModule(this))
                .build().inject(this);

        String longitude = getIntent().getStringExtra("EXTRA_LONGITUDE");
        String latitude = getIntent().getStringExtra("EXTRA_LATITUDE");

        currentPresenter.loadCurrentWeather(longitude, latitude);
    }

    @Override
    public void displayCurrentWeather(CurrentWeather currentWeather) {

        String city = currentWeather.getCityName() + ", "
                + GeneralMethods.convertCountryCodeToCountryName(currentWeather.getCityCountry());
        mLocation.setText(city);

        mDate.setText(GeneralMethods.convertUnixToDate(
                currentWeather.getDateOfCollection(), currentWeather.getCityCountry()));

        String imagePath = API.ICON_BASE + currentWeather.getWeatherIconId() + ".png";
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.noicon)
                .error(R.drawable.noicon)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(getApplicationContext())
                .load(imagePath)
                .apply(options)
                .into(mCurrWeatherIcon);

        mTemperature.setText(String.format(Locale.getDefault(),
                "%.1f ℃", currentWeather.getTemperature()));
        mDescription.setText(currentWeather.getWeatherDescription());
        mWindSpeed.setText(String.format(Locale.getDefault(),"%.1f", currentWeather.getWindSpeed()));
        mWindDirection.setText(GeneralMethods.convertDegreeToCardinalDirection(currentWeather.getWindDirection()));
        mPressure.setText(String.format("%s hPa", currentWeather.getPressure()));
        mHumidity.setText(String.format("Humidity: %s%%", currentWeather.getHumidity()));
        mCloudiness.setText(String.format("Cloudiness: %s%%", currentWeather.getCloudiness()));

        if(currentWeather.getRainVolume()!= 0.0f) {
            mRainVolume.setText(String.format(Locale.getDefault(),
                    "Rain: %.1f mm", currentWeather.getRainVolume()));
        }

        mSunrise.setText(String.format("Time of Sunrise: %s",
                GeneralMethods.convertUnixToTime(currentWeather.getTimeOfSunrise(), currentWeather.getCityCountry())));
        mSunset.setText(String.format("Time of Sunset: %s",
                GeneralMethods.convertUnixToTime(currentWeather.getTimeOfSunset(), currentWeather.getCityCountry())));

    }

    @Override
    public void setProgressBarVisible() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProgressBarInvisible() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toastNoDataFound() {
        Toast.makeText(getApplicationContext(),
                Messages.NO_DATA_FOUND, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toastNoInternetConnection() {
        Toast.makeText(getApplicationContext(),
                Messages.NO_CONNECTION, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeActivity() {
        finish();
    }
}
