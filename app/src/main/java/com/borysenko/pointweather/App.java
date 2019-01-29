package com.borysenko.pointweather;

import android.app.Application;

import com.borysenko.pointweather.dagger.AppComponent;
import com.borysenko.pointweather.dagger.DaggerAppComponent;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 13:45
 */
public class App extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .build();
    }

    public AppComponent getApplicationComponent() {
        return mAppComponent;
    }
}
