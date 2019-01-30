package com.borysenko.pointweather.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.borysenko.pointweather.R;
import com.borysenko.pointweather.model.WeatherItem;
import com.borysenko.pointweather.retrofit.API;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 22:08
 */
public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {

    private static WeatherItem[] mWeatherItems;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.date) TextView mDate;
        @BindView(R.id.weather_icon) ImageView mWeatherIcon;
        @BindView(R.id.curr_temperature) TextView mCurrTemperature;
        @BindView(R.id.pressure) TextView mPressure;
        @BindView(R.id.humidity) TextView mHumidity;
        @BindView(R.id.weather_description) TextView mWeatherDescription;
        @BindView(R.id.wind_speed) TextView mWindSpeed;
        @BindView(R.id.wind_deg) TextView mWindDeg;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public WeatherRecyclerAdapter(WeatherItem[] weatherItems, Context context) {
        mWeatherItems = weatherItems;
        mContext = context;
    }

    @NonNull
    @Override
    public WeatherRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_recycler_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder movieViewHolder, int i) {

        WeatherItem item = mWeatherItems[i];
        assert item != null;

        String imagePath = API.ICON_BASE + item.getWeatherIconId() + ".png";
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.noicon)
                .error(R.drawable.noicon)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(imagePath)
                .apply(options)
                .into(movieViewHolder.mWeatherIcon);

        movieViewHolder.mDate.setText(item.getDate());
        movieViewHolder.mCurrTemperature.setText(item.getCurrTemperature());
        movieViewHolder.mPressure.setText(item.getPressure());
        movieViewHolder.mHumidity.setText(item.getHumidity());
        movieViewHolder.mWeatherDescription.setText(item.getWeatherDescription());
        movieViewHolder.mWindSpeed.setText(item.getWindSpeed());
        movieViewHolder.mWindDeg.setText(item.getWindDeg());
    }

    @Override
    public int getItemCount() {
        return mWeatherItems.length;
    }
}
