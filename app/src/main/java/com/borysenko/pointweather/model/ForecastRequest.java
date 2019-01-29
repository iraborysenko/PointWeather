package com.borysenko.pointweather.model;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 16:24
 */
public class ForecastRequest {
    private String cityName;
    private String cityCountry;

    private WeatherItem[] weatherItems;

    public ForecastRequest(String cityName, String cityCountry,
                           WeatherItem[] weatherItems) {
        this.cityName = cityName;
        this.cityCountry = cityCountry;
        this.weatherItems = weatherItems;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public WeatherItem[] getWeatherItems() {
        return weatherItems;
    }
}
