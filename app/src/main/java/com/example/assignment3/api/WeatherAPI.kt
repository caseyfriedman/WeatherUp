package com.example.assignment3.api

import com.example.assignment3.data.Base
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherAPI {

    @GET("data/2.5/weather")
    fun getWeatherDetails(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Call<Base>

}
