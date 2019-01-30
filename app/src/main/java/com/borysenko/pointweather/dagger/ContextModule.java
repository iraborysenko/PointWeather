package com.borysenko.pointweather.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 30/01/19
 * Time: 13:59
 */
@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Provides
    public Context context(){ return context.getApplicationContext(); }
}
