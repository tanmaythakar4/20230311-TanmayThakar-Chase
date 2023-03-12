package com.dynamo.jpmc.weatherapp.network;

import androidx.annotation.NonNull;

import com.dynamo.jpmc.weatherapp.model.WeatherForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by tanmaythakar on 3/11/23.
 */
public interface WeatherAPI {
    @GET("/data/3.0/onecall")
    Call<WeatherForecast> getWeatherForecast(@NonNull @Query("lat") double latitude, @NonNull @Query("lon") double longitude, @Query("appid") String apiKey);
}
