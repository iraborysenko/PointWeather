package com.borysenko.pointweather.retrofit;

import com.borysenko.pointweather.model.ForecastRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 15:42
 */
public interface ApiInterface {

    @GET("forecast?units=metric")
    Call<ForecastRequest> getForecastFor5Days(@Query("lat") String latitude,
                                              @Query("lon") String longitude,
                                              @Query("appid") String apiKey);
}

