package com.dynamo.jpmc.weatherapp.ui.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.dynamo.jpmc.weatherapp.R;
import com.dynamo.jpmc.weatherapp.model.WeatherForecast;
import com.dynamo.jpmc.weatherapp.ui.WeatherViewModel;
import com.dynamo.jpmc.weatherapp.util.Resource;
import com.dynamo.jpmc.weatherapp.util.Status;

/**
 * Created by tanmaythakar on 3/11/23.
 */
public class TodayFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_today, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
    }

    private void initViewModel() {
        WeatherViewModel viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        viewModel.weatherForecastData.observe(requireActivity(), new Observer<Resource<WeatherForecast>>() {
            @Override
            public void onChanged(Resource<WeatherForecast> weatherForecastResource) {
                if(weatherForecastResource.status == Status.SUCCESS){
                    Toast.makeText(requireActivity(), "SUCCESS "+weatherForecastResource.data.toString(), Toast.LENGTH_LONG).show();
                }else if(weatherForecastResource.status == Status.LOADING){
                    Toast.makeText(requireActivity(), "Loading", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(requireActivity(), weatherForecastResource.message, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
