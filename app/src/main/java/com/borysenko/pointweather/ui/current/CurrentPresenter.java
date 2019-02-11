package com.borysenko.pointweather.ui.current;

import android.support.annotation.NonNull;

import com.borysenko.pointweather.model.CurrentWeather;
import com.borysenko.pointweather.utils.API;
import com.borysenko.pointweather.retrofit.ApiInterface;
import com.borysenko.pointweather.utils.GeneralMethods;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 10/02/19
 * Time: 12:01
 */
public class CurrentPresenter implements CurrentScreen.Presenter {

    private CurrentScreen.View mView;

    @Inject
    CurrentPresenter(CurrentScreen.View mView) {
        this.mView = mView;
    }

    @Inject
    ApiInterface apiInterface;

    @Override
    public void loadCurrentWeather(String longitude, String latitude) {
        mView.setProgressBarVisible();
        Call<CurrentWeather> call =
                apiInterface.getCurrentWeather(latitude, longitude, API.WEATHER_MAP_KEY);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather>call,
                                   @NonNull Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                if (currentWeather != null) {
                    mView.displayCurrentWeather(currentWeather);
                } else {
                    if (GeneralMethods.isOnline())
                        mView.toastNoInternetConnection();
                    else mView.toastNoDataFound();
                    mView.closeActivity();
                }

                mView.setProgressBarInvisible();
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather>call, @NonNull Throwable t) {
                t.printStackTrace();
                mView.toastNoDataFound();
                mView.setProgressBarInvisible();
                mView.closeActivity();
            }
        });
    }
}
