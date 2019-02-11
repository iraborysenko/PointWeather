package com.borysenko.pointweather.model;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 10/02/19
 * Time: 14:04
 */
public class CurrentWeather {

    private String cityName;
    private String cityCountry;
    private int dateOfCollection;
    private String weatherDescription;
    private String weatherIconId;
    private float temperature;
    private int pressure;
    private int humidity;
    private float windSpeed;
    private int windDirection;
    private int cloudiness;
    private float rainVolume;
    private int timeOfSunrise;
    private int timeOfSunset;

    public CurrentWeather(String cityName, String cityCountry,
                          int dateOfCollection,
                          String weatherDescription, String weatherIconId,
                          float temperature, int pressure, int humidity,
                          float windSpeed, int windDirection,
                          int cloudiness, float rainVolume,
                          int timeOfSunrise, int timeOfSunset) {
        this.cityName = cityName;
        this.cityCountry = cityCountry;
        this.dateOfCollection = dateOfCollection;
        this.weatherDescription = weatherDescription;
        this.weatherIconId = weatherIconId;
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.cloudiness = cloudiness;
        this.rainVolume = rainVolume;
        this.timeOfSunrise = timeOfSunrise;
        this.timeOfSunset = timeOfSunset;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCityCountry() {
        return cityCountry;
    }

    public int getDateOfCollection() {
        return dateOfCollection;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public String getWeatherIconId() {
        return weatherIconId;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public float getRainVolume() {
        return rainVolume;
    }

    public int getTimeOfSunrise() {
        return timeOfSunrise;
    }

    public int getTimeOfSunset() {
        return timeOfSunset;
    }
}
