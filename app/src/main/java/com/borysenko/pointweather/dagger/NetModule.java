package com.borysenko.pointweather.dagger;

import android.app.Application;

import com.borysenko.pointweather.model.ForecastRequest;
import com.borysenko.pointweather.model.WeatherItem;
import com.borysenko.pointweather.retrofit.API;
import com.borysenko.pointweather.retrofit.ApiInterface;
import com.borysenko.pointweather.retrofit.gson.ForecastRequestDeserializer;
import com.borysenko.pointweather.retrofit.gson.WeatherItemDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 14:41
 */
@Module
public class NetModule {

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(ForecastRequest.class, new ForecastRequestDeserializer())
                .registerTypeAdapter(WeatherItem.class, new WeatherItemDeserializer())
                .create();
    }

//    @Provides
//    @Singleton
//    Cache provideHttpCache(Application application) {
//        int cacheSize = 10 * 1024 * 1024;
//        return new Cache(application.getCacheDir(), cacheSize);
//    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(/*Cache cache*/) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(100, TimeUnit.SECONDS);
        client.readTimeout(55, TimeUnit.SECONDS);
        client.writeTimeout(55, TimeUnit.SECONDS);
//        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideApiInterface(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }
}
