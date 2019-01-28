package com.borysenko.pointweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 28/01/19
 * Time: 23:01
 */
public class ForecastActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forecast);
    }
}
