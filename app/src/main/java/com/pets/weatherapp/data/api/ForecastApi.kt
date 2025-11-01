package com.pets.weatherapp.data.api

import com.pets.weatherapp.data.model.CurrentForecastResponse
import com.pets.weatherapp.data.model.DailyForecastsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("forecasts/current")
    suspend fun getCurrentForecast(@Query("city") city: String): CurrentForecastResponse

    @GET("forecasts/forecast")
    suspend fun getDailyForecast(@Query("city") city: String): DailyForecastsResponse
}