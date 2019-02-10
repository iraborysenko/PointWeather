package com.borysenko.pointweather.ui.current;

import com.borysenko.pointweather.model.CurrentWeather;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 10/02/19
 * Time: 12:01
 */
public interface CurrentScreen {
    interface View {

        void displayCurrentWeather(CurrentWeather currentWeather);

        void setProgressBarVisible();

        void setProgressBarInvisible();

        void toastNoInternetConnection();

        void toastNoDataFound();

        void closeActivity();

    }

    interface Presenter {

        void loadCurrentWeather(String longitude, String latitude);

    }
}
