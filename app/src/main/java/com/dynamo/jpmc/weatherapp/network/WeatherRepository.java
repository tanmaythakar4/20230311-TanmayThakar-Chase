package com.dynamo.jpmc.weatherapp.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.dynamo.jpmc.weatherapp.BuildConfig;
import com.dynamo.jpmc.weatherapp.model.WeatherForecast;
import com.dynamo.jpmc.weatherapp.util.Resource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tanmaythakar on 3/11/23.
 */
public class WeatherRepository {

    private WeatherAPI weatherAPI;

    public WeatherRepository(WeatherAPI weatherAPI) {
        this.weatherAPI = weatherAPI;
    }

    public void getWeatherForecast(@NonNull double lat, @NonNull double lng, MutableLiveData<Resource<WeatherForecast>> liveData) {
        Call<WeatherForecast> call = weatherAPI.getWeatherForecast(lat, lng,ApiConstants.UNIT,BuildConfig.WEATHER_API_KEY);
        call.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                if (response.isSuccessful()) {
                    liveData.postValue(Resource.success(response.body()));
                } else {
                    liveData.postValue(Resource.error("Weather data is not there",null));
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                liveData.postValue(Resource.error(t.getLocalizedMessage(),null));
            }
        });
    }
}
