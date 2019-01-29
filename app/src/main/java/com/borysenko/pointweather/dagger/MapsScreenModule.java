package com.borysenko.pointweather.dagger;

import com.borysenko.pointweather.ui.map.MapsScreen;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 13:56
 */
@Module
public class MapsScreenModule {
    private final MapsScreen.View mView;

    public MapsScreenModule(MapsScreen.View mView) {
        this.mView = mView;
    }

    @Provides
    MapsScreen.View providesMapsScreenView() {
        return mView;
    }
}
