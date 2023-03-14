package com.dynamo.jpmc.weatherapp.ui.current;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dynamo.jpmc.weatherapp.R;
import com.dynamo.jpmc.weatherapp.databinding.FragmentWeatherHomeBinding;
import com.dynamo.jpmc.weatherapp.model.WeatherForecast;
import com.dynamo.jpmc.weatherapp.util.Resource;
import com.dynamo.jpmc.weatherapp.util.Status;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by tanmaythakar on 3/11/23.
 */
@AndroidEntryPoint
public class WeatherHomeFragment extends Fragment {

    WeeklyWeatherAdapter weeklyWeatherAdapter;
    HourlyWeatherAdapter hourlyWeatherAdapter;
    FragmentWeatherHomeBinding binding;
    WeatherViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupHourlyAdapter(requireContext());
        setupWeeklyAdapter(requireContext());
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
        initWeatherForecast();
    }

    /**
     * Setup a Recyclerview to show the weekly weather forecast data
     * @param context
     */
    private void setupWeeklyAdapter(@NonNull Context context) {
        DividerItemDecoration decoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        binding.weeklyRecyclerView.addItemDecoration(decoration);
        binding.weeklyRecyclerView.setHasFixedSize(true);
        binding.weeklyRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        weeklyWeatherAdapter = new WeeklyWeatherAdapter(daily -> {
        });
        binding.weeklyRecyclerView.setAdapter(weeklyWeatherAdapter);
    }

    /**
     * Setup a Recyclerview to show the hourly weather forecast data
     * @param context
     */
    private void setupHourlyAdapter(@NonNull Context context) {
        binding.hourlyRecyclerView.setHasFixedSize(true);
        binding.hourlyRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.hourlyRecyclerView.setAdapter(hourlyWeatherAdapter = new HourlyWeatherAdapter());
    }

    private void initWeatherForecast() {
        viewModel.weatherForecastData.observe(requireActivity(), weatherForecastResource -> {
            if (weatherForecastResource.status == Status.SUCCESS) {
                binding.root.setVisibility(View.VISIBLE);
                binding.loading.setVisibility(View.GONE);
                binding.setItem(weatherForecastResource.data);
                binding.setLocation(viewModel.getWsLocation());
                hourlyWeatherAdapter.setData(weatherForecastResource.data.getHourly());
                weeklyWeatherAdapter.setData(weatherForecastResource.data.getDaily());
            } else if (weatherForecastResource.status == Status.LOADING) {
                binding.root.setVisibility(View.GONE);
                binding.loading.setVisibility(View.VISIBLE);
                Toast.makeText(requireActivity(), "Loading", Toast.LENGTH_SHORT).show();
            } else {
                binding.root.setVisibility(View.GONE);
                binding.loading.setVisibility(View.VISIBLE);
                Toast.makeText(requireActivity(), weatherForecastResource.message, Toast.LENGTH_SHORT).show();
            }
        });



    }
}
