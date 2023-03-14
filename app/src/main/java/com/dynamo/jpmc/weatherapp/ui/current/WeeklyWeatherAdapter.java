package com.dynamo.jpmc.weatherapp.ui.current;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dynamo.jpmc.weatherapp.R;
import com.dynamo.jpmc.weatherapp.databinding.ItemWeeklyRowBinding;
import com.dynamo.jpmc.weatherapp.model.Daily;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tanmaythakar on 3/12/23.
 */
public class WeeklyWeatherAdapter extends RecyclerView.Adapter<WeeklyWeatherAdapter.ViewHolder> {

    @NonNull
    private List<Daily> weeklyWeather = new ArrayList<>();

    @NonNull
    WeeklyWeatherAdapter.OnDailyWeatherSelectListener listener;

    public WeeklyWeatherAdapter(@NonNull WeeklyWeatherAdapter.OnDailyWeatherSelectListener listener) {
        this.listener = listener;
    }

    public void setData(@NonNull List<Daily> weeklyWeather) {
        // Sort the weekly weather data in ascending and Filter with current time
        this.weeklyWeather = weeklyWeather.stream().filter(hourly -> (hourly.getDt() * 1000) > System.currentTimeMillis()).collect(Collectors.toList());
        Collections.sort(weeklyWeather, (d1, d2) -> (new Date(d1.getDt() * 1000).compareTo(new Date(d2.getDt() * 1000))));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeeklyWeatherAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weekly_row, parent, false);
        return new WeeklyWeatherAdapter.ViewHolder(DataBindingUtil.bind(view));
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyWeatherAdapter.ViewHolder holder, int position) {
        holder.bind(weeklyWeather.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return weeklyWeather.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        ItemWeeklyRowBinding binding;

        private ViewHolder(ItemWeeklyRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(@NonNull Daily item, @NonNull WeeklyWeatherAdapter.OnDailyWeatherSelectListener listener) {
            binding.setItem(item);
        }
    }

    public interface OnDailyWeatherSelectListener {
        void onDailyWeatherSelected(@NonNull Daily device);
    }
}