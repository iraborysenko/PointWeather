package com.borysenko.pointweather.retrofit.gson;

import com.borysenko.pointweather.model.WeatherItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DecimalFormat;


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
        String weatherDescription;
        String weatherIconId;
        String cloudiness;
        String windSpeed;
        String windDeg;

        DecimalFormat df = new DecimalFormat("0.0");

        JsonObject jsonObject = json.getAsJsonObject();

        date = jsonObject.get("dt_txt").getAsString();

        JsonObject main = jsonObject.get("main").getAsJsonObject();
        currTemperature =  df.format(main.get("temp").getAsFloat());
        pressure = String.valueOf(Math.round(main.get("pressure").getAsFloat()));
        humidity = main.get("humidity").getAsString();

        JsonArray weatherArray = jsonObject.get("weather").getAsJsonArray();
        JsonObject weather = weatherArray.get(0).getAsJsonObject();

        weatherDescription = weather.get("description").getAsString();
        weatherIconId = weather.get("icon").getAsString();

        JsonObject clouds = jsonObject.get("clouds").getAsJsonObject();
        cloudiness = clouds.get("all").getAsString();

        JsonObject wind = jsonObject.get("wind").getAsJsonObject();
        windSpeed = df.format(wind.get("speed").getAsFloat());
        windDeg = convertDegreeToCardinalDirection(Math.round(wind.get("deg").getAsFloat()));

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

    private String convertDegreeToCardinalDirection(int directionInDegrees){
        String cardinalDirection = null;
        if((directionInDegrees >= 348.75) && (directionInDegrees <= 360) ||
                (directionInDegrees >= 0) && (directionInDegrees <= 11.25)){
            cardinalDirection = "N";
        } else if( (directionInDegrees >= 11.25 ) && (directionInDegrees <= 33.75)){
            cardinalDirection = "NNE";
        } else if( (directionInDegrees >= 33.75 ) &&(directionInDegrees <= 56.25)){
            cardinalDirection = "NE";
        } else if( (directionInDegrees >= 56.25 ) && (directionInDegrees <= 78.75)){
            cardinalDirection = "ENE";
        } else if( (directionInDegrees >= 78.75 ) && (directionInDegrees <= 101.25) ){
            cardinalDirection = "E";
        } else if( (directionInDegrees >= 101.25) && (directionInDegrees <= 123.75) ){
            cardinalDirection = "ESE";
        } else if( (directionInDegrees >= 123.75) && (directionInDegrees <= 146.25) ){
            cardinalDirection = "SE";
        } else if( (directionInDegrees >= 146.25) && (directionInDegrees <= 168.75) ){
            cardinalDirection = "SSE";
        } else if( (directionInDegrees >= 168.75) && (directionInDegrees <= 191.25) ){
            cardinalDirection = "S";
        } else if( (directionInDegrees >= 191.25) && (directionInDegrees <= 213.75) ){
            cardinalDirection = "SSW";
        } else if( (directionInDegrees >= 213.75) && (directionInDegrees <= 236.25) ){
            cardinalDirection = "SW";
        } else if( (directionInDegrees >= 236.25) && (directionInDegrees <= 258.75) ){
            cardinalDirection = "WSW";
        } else if( (directionInDegrees >= 258.75) && (directionInDegrees <= 281.25) ){
            cardinalDirection = "W";
        } else if( (directionInDegrees >= 281.25) && (directionInDegrees <= 303.75) ){
            cardinalDirection = "WNW";
        } else if( (directionInDegrees >= 303.75) && (directionInDegrees <= 326.25) ){
            cardinalDirection = "NW";
        } else if( (directionInDegrees >= 326.25) && (directionInDegrees <= 348.75) ){
            cardinalDirection = "NNW";
        } else {
            cardinalDirection = "?";
        }
        return cardinalDirection;
    }
}
