package com.borysenko.pointweather.dagger;

import android.content.Context;

import com.borysenko.pointweather.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 13:52
 */
@Module
public class AppModule {
    private final App mApp;

    public AppModule(App mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    App app() {
        return mApp;
    }

    @Provides
    @Singleton
    Context applicationContext() {
        return mApp.getApplicationContext();
    }
}
