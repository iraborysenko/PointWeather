package com.borysenko.pointweather.ui.map;

import javax.inject.Inject;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 14:06
 */
public class MapsPresenter implements MapsScreen.Presenter {

    private MapsScreen.View mView;

    @Inject
    MapsPresenter(MapsScreen.View mView) {
        this.mView = mView;
    }

    @Override
    public void getEnteredData(String query) {
        mView.findPlace(query);
    }
}
