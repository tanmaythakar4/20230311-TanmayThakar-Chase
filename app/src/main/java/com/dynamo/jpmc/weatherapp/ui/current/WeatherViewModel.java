package com.dynamo.jpmc.weatherapp.ui.current;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dynamo.jpmc.weatherapp.model.WeatherForecast;
import com.dynamo.jpmc.weatherapp.model.WsLocation;
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
    private WsLocation wsLocation;
    public
    WeatherRepository weatherRepository;

    @Inject
    public WeatherViewModel(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.weatherForecastData = new MutableLiveData<>();
        updateCurrentLocation(getWsLocation());
    }


    public void updateCurrentLocation(WsLocation wsLocation) {
        this.wsLocation = wsLocation;
        getWeatherForecastData();
    }

    public WsLocation getWsLocation() {
        if (wsLocation == null) {
            wsLocation = new WsLocation(41.640640, -72.683113, "Rocky Hill");
        }
        return wsLocation;
    }

    public void getWeatherForecastData() {
        weatherForecastData.postValue(Resource.loading(null));
        weatherRepository.getWeatherForecast(getWsLocation().getLat(), getWsLocation().getLon(), weatherForecastData);
    }

    /**
     * Re fetch the current {@link WeatherForecast}
     */
    public void refreshWeather() {
        getWeatherForecastData();
    }
}
