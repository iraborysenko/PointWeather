package com.borysenko.pointweather.ui.forecast;

import android.support.annotation.NonNull;
import com.borysenko.pointweather.model.ForecastRequest;
import com.borysenko.pointweather.model.WeatherItem;
import com.borysenko.pointweather.retrofit.API;
import com.borysenko.pointweather.retrofit.ApiInterface;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 14:09
 */
public class ForecastPresenter implements ForecastScreen.Presenter {
    private ForecastScreen.View mView;

    @Inject
    ForecastPresenter(ForecastScreen.View mView) {
        this.mView = mView;
    }

    @Inject
    ApiInterface apiInterface;

    @Override
    public void loadForecastFor5Days(String longitude, String latitude) {
        mView.setProgressBarVisible();
        Call<ForecastRequest> call =
                apiInterface.getForecastFor5Days(latitude, longitude, API.WEATHER_MAP_KEY);
        call.enqueue(new Callback<ForecastRequest>() {
            @Override
            public void onResponse(@NonNull Call<ForecastRequest>call,
                                   @NonNull Response<ForecastRequest> response) {
                ForecastRequest forecast = response.body();
                if (forecast != null) {
                    WeatherItem[] items = forecast.getWeatherItems();
                    mView.setCityData(forecast.getCityName(), forecast.getCityCountry());
                    mView.initRecyclerView(items);

                } else {
                    if (!isOnline())
                        mView.toastNoInternetConnection();
                    else mView.toastNoDataFound();
                    mView.closeActivity();
                }

                mView.setProgressBarInvisible();
            }

            @Override
            public void onFailure(@NonNull Call<ForecastRequest>call, @NonNull Throwable t) {
                t.printStackTrace();
                mView.toastNoDataFound();
                mView.setProgressBarInvisible();
                mView.closeActivity();
            }
        });
    }

    static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }
}
