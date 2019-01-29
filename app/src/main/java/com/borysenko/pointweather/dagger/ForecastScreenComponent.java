package com.borysenko.pointweather.dagger;

import com.borysenko.pointweather.ui.forecast.ForecastActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 14:10
 */
@Singleton
@Component(modules = ForecastScreenModule.class)
public interface ForecastScreenComponent {
    void inject(ForecastActivity forecastActivity);
}
