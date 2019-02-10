package com.borysenko.pointweather.dagger;


import android.content.Context;

import com.borysenko.pointweather.model.CurrentWeather;
import com.borysenko.pointweather.model.ForecastRequest;
import com.borysenko.pointweather.model.WeatherItem;
import com.borysenko.pointweather.retrofit.API;
import com.borysenko.pointweather.retrofit.ApiInterface;
import com.borysenko.pointweather.retrofit.gson.CurrentWeatherDeserializer;
import com.borysenko.pointweather.retrofit.gson.ForecastRequestDeserializer;
import com.borysenko.pointweather.retrofit.gson.WeatherItemDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 14:41
 */
@Module(includes = ContextModule.class)
public class NetModule {

    @Provides
    @Singleton
    File provideCacheFile(Context context){
        return new File(context.getCacheDir(), "HttpCache");
    }

    @Provides
    @Singleton
    Cache provideHttpCache(File cacheDirectory) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(cacheDirectory, cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(50, TimeUnit.SECONDS);
        client.readTimeout(55, TimeUnit.SECONDS);
        client.writeTimeout(55, TimeUnit.SECONDS);
        client.cache(cache);
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                try {
                    return chain.proceed(chain.request());
                } catch (Exception e) {
                    Request offlineRequest = chain.request().newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24)
                            .build();
                    return chain.proceed(offlineRequest);
                }
            }
        });
        return client.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(ForecastRequest.class, new ForecastRequestDeserializer())
                .registerTypeAdapter(WeatherItem.class, new WeatherItemDeserializer())
                .registerTypeAdapter(CurrentWeather.class, new CurrentWeatherDeserializer())
                .create();
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
