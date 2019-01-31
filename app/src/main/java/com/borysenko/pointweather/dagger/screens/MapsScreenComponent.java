package com.borysenko.pointweather.dagger.screens;

import com.borysenko.pointweather.ui.map.MapsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 13:54
 */
@Singleton
@Component(modules = MapsScreenModule.class)
public interface MapsScreenComponent {
    void inject(MapsActivity mapActivity);
}
