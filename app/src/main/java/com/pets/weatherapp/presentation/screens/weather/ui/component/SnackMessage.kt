package com.pets.weatherapp.presentation.screens.weather.ui.component

sealed class SnackMessage {
    object NoInternet : SnackMessage()
    object LoadError : SnackMessage()
    data class CustomSnackMessage(val text: String) : SnackMessage()
}