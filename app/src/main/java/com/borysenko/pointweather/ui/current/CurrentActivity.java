package com.borysenko.pointweather.ui.current;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.borysenko.pointweather.R;
import com.borysenko.pointweather.dagger.ContextModule;
import com.borysenko.pointweather.dagger.screens.CurrentScreenModule;
import com.borysenko.pointweather.dagger.screens.DaggerCurrentScreenComponent;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 10/02/19
 * Time: 12:01
 */
public class CurrentActivity extends AppCompatActivity implements CurrentScreen.View {

    @Inject
    CurrentPresenter currentPresenter;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_current);
        ButterKnife.bind(this);

        DaggerCurrentScreenComponent.builder()
                .currentScreenModule(new CurrentScreenModule(this))
                .contextModule(new ContextModule(this))
                .build().inject(this);
    }
}
