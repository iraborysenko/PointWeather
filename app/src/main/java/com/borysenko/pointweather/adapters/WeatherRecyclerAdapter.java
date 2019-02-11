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
import com.borysenko.pointweather.utils.API;
import com.borysenko.pointweather.utils.GeneralMethods;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Locale;

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
        @BindView(R.id.cloudiness) TextView mCloudiness;
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
        movieViewHolder.mCurrTemperature.setText(String.format(Locale.getDefault(),"%.1f ℃", item.getCurrTemperature()));
        movieViewHolder.mPressure.setText(String.format("%s hPa", item.getPressure()));
        movieViewHolder.mHumidity.setText(String.format("H: %s%%", item.getHumidity()));
        movieViewHolder.mCloudiness.setText(String.format("C: %s%%", item.getCloudiness()));
        movieViewHolder.mWeatherDescription.setText(item.getWeatherDescription());
        movieViewHolder.mWindSpeed.setText(String.format(Locale.getDefault(),"%.1f m/s", item.getWindSpeed()));
        movieViewHolder.mWindDeg.setText(GeneralMethods.convertDegreeToCardinalDirection(item.getWindDeg()));
        movieViewHolder.itemView.setBackgroundColor(mContext.getResources()
                .getColor(R.color.colorLightGrey));
    }

    @Override
    public int getItemCount() {
        return mWeatherItems.length;
    }
}
