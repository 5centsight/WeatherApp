package com.pets.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pets.weatherapp.presentation.screens.CitySearchScreen
import com.pets.weatherapp.presentation.screens.WeatherScreen
import com.pets.weatherapp.presentation.viewmodel.ForecastViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val forecastViewModel: ForecastViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Screen.Weather.route
    ) {
        composable(route = Screen.Weather.route) {
            WeatherScreen(
                viewModel = forecastViewModel,
                onSearchClick = { navController.navigate(Screen.CitySearch.route) }
            )
        }

        composable(route = Screen.CitySearch.route) {
            CitySearchScreen(
                viewModel = forecastViewModel,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}