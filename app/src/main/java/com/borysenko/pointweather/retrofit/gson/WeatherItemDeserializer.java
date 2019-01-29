package com.borysenko.pointweather.retrofit.gson;

import com.borysenko.pointweather.model.WeatherItem;
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
 * Date: 29/01/19
 * Time: 18:15
 */
public class WeatherItemDeserializer implements JsonDeserializer {
    @Override
    public WeatherItem deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException {

        String date;
        String currTemperature;
        String pressure;
        String humidity;
        String weatherId;
        String weatherMain;
        String weatherDescription;
        String weatherIconId;
        String clouds;
        String windSpeed;
        String windDeg;

        JsonObject jsonObject = json.getAsJsonObject();

        date = jsonObject.get("dt_txt").getAsString();

        JsonObject main = jsonObject.get("main").getAsJsonObject();
        currTemperature = main.get("temp").getAsString();
        pressure = main.get("pressure").getAsString();
        humidity = main.get("humidity").getAsString();

        JsonArray weatherArray = jsonObject.get("weather").getAsJsonArray();
        JsonObject weather = weatherArray.get(0).getAsJsonObject();

        weatherId = weather.get("id").getAsString();
        weatherMain = weather.get("main").getAsString();
        weatherDescription = weather.get("description").getAsString();
        weatherIconId = weather.get("icon").getAsString();

        JsonObject cloudiness = jsonObject.get("clouds").getAsJsonObject();
        clouds = cloudiness.get("all").getAsString();

        JsonObject wind = jsonObject.get("wind").getAsJsonObject();
        windSpeed = wind.get("speed").getAsString();
        windDeg = wind.get("deg").getAsString();

        return new WeatherItem(
                date,
                currTemperature,
                pressure,
                humidity,
                weatherId,
                weatherMain,
                weatherDescription,
                weatherIconId,
                clouds,
                windSpeed,
                windDeg
        );
    }
}

