package com.pets.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.pets.weatherapp.presentation.viewmodel.ForecastViewModel
import com.pets.weatherapp.presentation.screens.MainScreen
import com.pets.weatherapp.presentation.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: ForecastViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

