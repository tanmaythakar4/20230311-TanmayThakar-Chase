package com.dynamo.jpmc.weatherapp.di

import com.dynamo.jpmc.weatherapp.network.ApiConstants
import com.dynamo.jpmc.weatherapp.network.WeatherAPI
import com.dynamo.jpmc.weatherapp.network.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by tanmaythakar on 3/11/23.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideWeatherApi(builder: Retrofit.Builder, okHttpClient: OkHttpClient): WeatherAPI {
        return builder.client(okHttpClient).build().create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherAPI: WeatherAPI): WeatherRepository {
        return WeatherRepository(weatherAPI)
    }
}