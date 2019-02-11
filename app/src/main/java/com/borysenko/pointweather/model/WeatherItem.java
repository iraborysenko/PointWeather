package com.borysenko.pointweather.model;


/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 29/01/19
 * Time: 18:00
 */
public class WeatherItem {
    private String date;
    private float currTemperature;
    private int pressure;
    private int humidity;
    private String weatherDescription;
    private String weatherIconId;
    private int cloudiness;
    private float windSpeed;
    private int windDeg;

    public WeatherItem(String date,
                       float currTemperature,
                       int pressure, int humidity,
                       String weatherDescription, String weatherIconId,
                       int cloudiness,
                       float windSpeed, int windDeg) {
        this.date = date;
        this.currTemperature = currTemperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weatherDescription = weatherDescription;
        this.weatherIconId = weatherIconId;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
        this.windDeg = windDeg;
    }

    public String getDate() {
        return date;
    }

    public float getCurrTemperature() {
        return currTemperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherIconId() {
        return weatherIconId;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public int getWindDeg() {
        return windDeg;
    }
}
