package com.pets.weatherapp.data.api

import com.pets.weatherapp.data.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {
    @GET("forecasts/current")
    suspend fun getCurrentForecast(@Query("city") city: String): ForecastResponse
}