package com.borysenko.pointweather.ui.forecast;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 14:09
 */
public class ForecastPresenter implements ForecastScreen.Presenter {
    private ForecastScreen.View mView;

    @Inject
    ForecastPresenter(ForecastScreen.View mView) {
        this.mView = mView;
    }
}
