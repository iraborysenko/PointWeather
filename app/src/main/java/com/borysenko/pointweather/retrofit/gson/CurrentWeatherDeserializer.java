package com.borysenko.pointweather.retrofit.gson;

import com.borysenko.pointweather.model.CurrentWeather;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 10/02/19
 * Time: 14:41
 */
public class CurrentWeatherDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        String cityName;
        String cityCountry;
        int dateOfCollection;
        String weatherDescription;
        String weatherIconId;
        float temperature;
        int pressure;
        int humidity;
        float windSpeed;
        int windDirection;
        int cloudiness;
        float rainVolume = 0.0f;
        int timeOfSunrise;
        int timeOfSunset;

        JsonObject jsonObject = json.getAsJsonObject();

        cityName = jsonObject.get("name").getAsString();

        JsonObject sys = jsonObject.get("sys").getAsJsonObject();
        cityCountry =  sys.get("country").getAsString();
        timeOfSunrise = sys.get("sunrise").getAsInt();
        timeOfSunset = sys.get("sunset").getAsInt();

        dateOfCollection = jsonObject.get("dt").getAsInt();

        JsonArray weatherArray = jsonObject.get("weather").getAsJsonArray();
        JsonObject weather = weatherArray.get(0).getAsJsonObject();
        weatherDescription = weather.get("description").getAsString();
        weatherIconId = weather.get("icon").getAsString();

        JsonObject main = jsonObject.get("main").getAsJsonObject();
        temperature =  main.get("temp").getAsFloat();
        pressure = Math.round(main.get("pressure").getAsFloat());
        humidity = main.get("humidity").getAsInt();

        JsonObject wind = jsonObject.get("wind").getAsJsonObject();
        windSpeed = wind.get("speed").getAsFloat();
        windDirection =Math.round(wind.get("deg").getAsFloat());

        JsonObject clouds = jsonObject.get("clouds").getAsJsonObject();
        cloudiness = clouds.get("all").getAsInt();

        if (jsonObject.get("rain")!=null) {
            JsonObject rain = jsonObject.get("rain").getAsJsonObject();
            rainVolume = rain.get("3h").getAsInt();
        }

        return new CurrentWeather(
                cityName,
                cityCountry,
                dateOfCollection,
                weatherDescription,
                weatherIconId,
                temperature,
                pressure,
                humidity,
                windSpeed,
                windDirection,
                cloudiness,
                rainVolume,
                timeOfSunrise,
                timeOfSunset
        );
    }
}
