package com.dynamo.jpmc.weatherapp.model

import android.icu.text.SimpleDateFormat
import java.text.DateFormat
import java.util.*

data class Current(
    val clouds: Int,
    val dew_point: Double,
    val dt: Long,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
){
    val formattedDate: String
        get() = SimpleDateFormat("EEEE").format(Date(dt*1000)) + " "+SimpleDateFormat("dd MMM yyyy ").format(dt*1000)
}