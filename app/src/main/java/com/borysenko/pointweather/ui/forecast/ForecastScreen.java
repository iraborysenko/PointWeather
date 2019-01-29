package com.borysenko.pointweather.ui.forecast;


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
    }

    interface Presenter {

        void loadForecastFor5Days(String longitude, String latitude);

    }
}