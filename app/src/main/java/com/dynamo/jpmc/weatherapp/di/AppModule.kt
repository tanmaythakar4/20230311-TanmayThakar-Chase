package com.dynamo.jpmc.weatherapp.di

/**
 * Created by tanmaythakar on 3/11/23.
 */

//@Module
//@InstallIn(SingletonComponent::class)
object AppModule {

    /* private val retrofit by lazy {
         val logging = HttpLoggingInterceptor()
         logging.setLevel(HttpLoggingInterceptor.Level.BODY)
         val client = OkHttpClient.Builder()
             .addInterceptor(logging)
             .build()
         Retrofit.Builder()
             .baseUrl(Constants.BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .client(client)
             .build()
     }

     @Provides
     @Singleton
     fun getWeatherApi(): WeatherAPI {
         return retrofit.create(WeatherAPI::class.java)
     }

     @Provides
     @Singleton
     fun getWeatherRepository(): WeatherRepository {
         return WeatherRepository(getWeatherApi())
     }*/
}