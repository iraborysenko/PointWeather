package com.borysenko.pointweather.model;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 18:00
 */
public class WeatherItem {
    private String date;
    private String currTemperature;
    private String pressure;
    private String humidity;
    private String weatherId;
    private String weatherMain;
    private String weatherDescription;
    private String weatherIconId;
    private String clouds;
    private String windSpeed;
    private String windDeg;

    public WeatherItem(String date,
                       String currTemperature, String pressure, String humidity,
                       String weatherId, String weatherMain,
                       String weatherDescription, String weatherIconId,
                       String clouds,
                       String windSpeed, String windDeg) {
        this.date = date;
        this.currTemperature = currTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weatherId = weatherId;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
        this.weatherIconId = weatherIconId;
        this.clouds = clouds;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
    }

    public String getDate() {
        return date;
    }

    public String getCurrTemperature() {
        return currTemperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherIconId() {
        return weatherIconId;
    }

    public String getClouds() {
        return clouds;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDeg() {
        return windDeg;
    }
}
