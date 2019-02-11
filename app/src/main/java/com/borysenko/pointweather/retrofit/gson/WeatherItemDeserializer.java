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
        float currTemperature;
        int pressure;
        int humidity;
        String weatherDescription;
        String weatherIconId;
        int cloudiness;
        float windSpeed;
        int windDeg;

        JsonObject jsonObject = json.getAsJsonObject();

        date = jsonObject.get("dt_txt").getAsString();

        JsonObject main = jsonObject.get("main").getAsJsonObject();
        currTemperature =  main.get("temp").getAsFloat();
        pressure = Math.round(main.get("pressure").getAsFloat());
        humidity = main.get("humidity").getAsInt();

        JsonArray weatherArray = jsonObject.get("weather").getAsJsonArray();
        JsonObject weather = weatherArray.get(0).getAsJsonObject();

        weatherDescription = weather.get("description").getAsString();
        weatherIconId = weather.get("icon").getAsString();

        JsonObject clouds = jsonObject.get("clouds").getAsJsonObject();
        cloudiness = clouds.get("all").getAsInt();

        JsonObject wind = jsonObject.get("wind").getAsJsonObject();
        windSpeed = wind.get("speed").getAsFloat();
        windDeg = Math.round(wind.get("deg").getAsFloat());

        return new WeatherItem(
                date,
                currTemperature,
                pressure,
                humidity,
                weatherDescription,
                weatherIconId,
                cloudiness,
                windSpeed,
                windDeg
        );
    }
}
