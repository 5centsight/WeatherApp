package com.pets.weatherapp.presentation.navigation

sealed class Screen(val route: String) {
    object Weather: Screen("weather")
    object CitySearch: Screen("city_search")
}