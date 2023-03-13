package com.dynamo.jpmc.weatherapp.model

import android.icu.text.SimpleDateFormat
import java.util.*

data class Hourly(
    val clouds: Int,
    val dew_point: Double,
    val dt: Long,
    val feels_like: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
){
    val formattedDate: String
        get() = SimpleDateFormat("h aa").format(dt*1000)
}