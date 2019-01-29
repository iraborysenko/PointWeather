package com.borysenko.pointweather.dagger;

import com.borysenko.pointweather.ui.forecast.ForecastScreen;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 14:11
 */
@Module(includes = NetModule.class)
public class ForecastScreenModule {
    private final ForecastScreen.View mView;

    public ForecastScreenModule(ForecastScreen.View mView) {
        this.mView = mView;
    }

    @Provides
    ForecastScreen.View providesForecastScreenView() {
        return mView;
    }
}
