package com.pets.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pets.weatherapp.presentation.screens.citysearch.ui.CitySearchScreen
import com.pets.weatherapp.presentation.screens.weather.ui.WeatherScreen
import com.pets.weatherapp.presentation.screens.weather.presentation.ForecastViewModel
import com.pets.weatherapp.presentation.screens.citysearch.presentation.SearchViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val forecastViewModel: ForecastViewModel = koinViewModel()
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
            val searchViewModel: SearchViewModel = koinViewModel()
            CitySearchScreen(
                viewModel = forecastViewModel,
                searchViewModel = searchViewModel,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}