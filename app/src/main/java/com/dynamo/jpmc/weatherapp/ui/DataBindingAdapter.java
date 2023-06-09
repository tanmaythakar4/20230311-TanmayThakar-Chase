package com.dynamo.jpmc.weatherapp.ui;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.dynamo.jpmc.weatherapp.R;
import com.dynamo.jpmc.weatherapp.network.ApiConstants;

/**
 * Created by tanmaythakar on 3/12/23.
 */
public class DataBindingAdapter {

    /**
     * to Get the Weather Icon
     *
     * @param imageView
     * @param weatherIconUrl
     */
    @BindingAdapter(value = {"weatherIconUrl"}, requireAll = false)
    public static void loadImage(@NonNull ImageView imageView, @NonNull String weatherIconUrl) {
        Glide.with(imageView.getContext()).load(ApiConstants.BASE_IMAGE_URL + weatherIconUrl + ApiConstants.SERVER_IMAGE_RESOLUTION).into(imageView);
    }

    /**
     * to format the temperature field
     *
     * @param textView
     * @param temperature raw value of temp
     */
    @BindingAdapter(value = {"formatTemperature"})
    public static void formattedTemp(@NonNull TextView textView, @NonNull Double temperature) {
        textView.setText(String.format(textView.getContext().getString(R.string.format_temperature), Math.floor(temperature)));
    }

    /**
     * to format the wind field
     *
     * @param textView
     * @param wind     raw value of wind
     */
    @BindingAdapter(value = {"formatWind"})
    public static void formattedWind(@NonNull TextView textView, @NonNull Double wind) {
        textView.setText(String.format(textView.getContext().getString(R.string.format_wind_mph), Math.floor(wind)));
    }

    /**
     * to format a field with min/max temperature
     *
     * @param textView
     * @param minTemp
     * @param maxTemp
     */
    @BindingAdapter(value = {"formatMinTemp", "formatMaxTemp"})
    public static void formattedMinMaxTemp(@NonNull TextView textView, @NonNull Double minTemp, @NonNull Double maxTemp) {
        textView.setText(String.format(textView.getContext().getString(R.string.format_min_max_temperature), Math.floor(minTemp), Math.floor(maxTemp)));
    }
}