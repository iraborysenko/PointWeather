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
    private String weatherDescription;
    private String weatherIconId;
    private String cloudiness;
    private String windSpeed;
    private String windDeg;

    public WeatherItem(String date,
                       String currTemperature, String pressure, String humidity,
                       String weatherDescription, String weatherIconId,
                       String cloudiness,
                       String windSpeed, String windDeg) {
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

    public String getCurrTemperature() {
        return currTemperature;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherIconId() {
        return weatherIconId;
    }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindDeg() {
        return windDeg;
    }
}
