package com.borysenko.pointweather.retrofit.gson;

import com.borysenko.pointweather.model.ForecastRequest;
import com.borysenko.pointweather.model.WeatherItem;
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
 * Time: 18:13
 */
public class ForecastRequestDeserializer implements JsonDeserializer {
    @Override
    public ForecastRequest deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        String cityName;
        String cityCountry;

        WeatherItem[] weatherItems;

        JsonObject jsonObject = json.getAsJsonObject();

        JsonObject city = jsonObject.get("city").getAsJsonObject();
        cityName = city.get("name").getAsString();
        cityCountry = city.get("country").getAsString();

        weatherItems = context.deserialize(jsonObject.get("list"),
                WeatherItem[].class);

        return new ForecastRequest(
                cityName,
                cityCountry,
                weatherItems
        );
    }
}
