package com.dynamo.jpmc.weatherapp.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dynamo.jpmc.weatherapp.R;
import com.dynamo.jpmc.weatherapp.databinding.ItemHourlyRowBinding;
import com.dynamo.jpmc.weatherapp.model.Hourly;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tanmaythakar on 3/12/23.
 */

public class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherAdapter.ViewHolder> {

    @NonNull
    private List<Hourly> hourlyWeather = new ArrayList<>();

    public void setData(@NonNull List<Hourly> hourlyWeather) {
        this.hourlyWeather = hourlyWeather.stream().filter(hourly -> (hourly.getDt()*1000)>=System.currentTimeMillis()).collect(Collectors.toList());
        Collections.sort(hourlyWeather, (h1, h2) -> (new Date(h2.getDt() * 1000).compareTo(new Date(h1.getDt() * 1000))));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HourlyWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hourly_row, parent, false);
        return new HourlyWeatherAdapter.ViewHolder(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyWeatherAdapter.ViewHolder holder, int position) {
        holder.bind(hourlyWeather.get(position));
    }

    @Override
    public int getItemCount() {
        return hourlyWeather.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        ItemHourlyRowBinding binding;

        private ViewHolder(ItemHourlyRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(@NonNull Hourly item) {
            binding.setItem(item);
        }
    }
}