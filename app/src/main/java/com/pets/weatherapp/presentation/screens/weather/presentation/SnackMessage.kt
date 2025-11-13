package com.pets.weatherapp.presentation.screens.weather.presentation

sealed class SnackMessage {
    object NoInternet : SnackMessage()
    object LoadError : SnackMessage()
    data class CustomSnackMessage(val text: String) : SnackMessage()
}