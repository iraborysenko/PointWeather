package com.borysenko.pointweather.dagger.screens;

import com.borysenko.pointweather.dagger.ContextModule;
import com.borysenko.pointweather.ui.current.CurrentActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 10/02/19
 * Time: 12:03
 */
@Singleton
@Component(modules = {ContextModule.class, CurrentScreenModule.class})
public interface CurrentScreenComponent {
    void inject(CurrentActivity currentActivity);
}

