package com.dynamo.jpmc.weatherapp.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dynamo.jpmc.weatherapp.model.WeatherForecast;
import com.dynamo.jpmc.weatherapp.network.WeatherRepository;
import com.dynamo.jpmc.weatherapp.util.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


/**
 * Created by tanmaythakar on 3/11/23.
 */
@HiltViewModel
public class WeatherViewModel extends ViewModel {

    public MutableLiveData<Resource<WeatherForecast>> weatherForecastData;
    WeatherRepository weatherRepository;

    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.weatherForecastData = new MutableLiveData<>();
        getWeatherForecastData(41.640640,-72.683113);
    }

    public void getWeatherForecastData(double lat, double lng) {
        weatherForecastData.postValue(Resource.loading(null));
        weatherRepository.getWeatherForecast(lat, lng, weatherForecastData);
    }
}
