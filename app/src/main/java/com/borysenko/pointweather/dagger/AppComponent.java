package com.borysenko.pointweather.dagger;

import com.borysenko.pointweather.App;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 13:52
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App app);
}