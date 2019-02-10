package com.borysenko.pointweather.ui.current;

import com.borysenko.pointweather.retrofit.ApiInterface;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 10/02/19
 * Time: 12:01
 */
public class CurrentPresenter implements CurrentScreen.Presenter {

    private CurrentScreen.View mView;

    @Inject
    CurrentPresenter(CurrentScreen.View mView) {
        this.mView = mView;
    }

    @Inject
    ApiInterface apiInterface;
}
