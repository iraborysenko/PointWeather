package com.borysenko.pointweather.ui.forecast;


import com.borysenko.pointweather.model.WeatherItem;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 14:08
 */
public interface ForecastScreen {
    interface View {

        void setProgressBarVisible();

        void setProgressBarInvisible();

        void initRecyclerView(WeatherItem[] items);

        void setCityData(String cityName, String cityCountry);
    }

    interface Presenter {

        void loadForecastFor5Days(String longitude, String latitude);

    }
}