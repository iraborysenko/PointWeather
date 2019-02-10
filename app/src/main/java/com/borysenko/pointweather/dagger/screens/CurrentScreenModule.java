package com.borysenko.pointweather.dagger.screens;

import com.borysenko.pointweather.dagger.NetModule;
import com.borysenko.pointweather.ui.current.CurrentScreen;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 10/02/19
 * Time: 12:04
 */
@Module(includes = NetModule.class)
public class CurrentScreenModule {
    private final CurrentScreen.View mView;

    public CurrentScreenModule(CurrentScreen.View mView) {
        this.mView = mView;
    }

    @Provides
    CurrentScreen.View providesCurrentScreenView() {
        return mView;
    }
}
