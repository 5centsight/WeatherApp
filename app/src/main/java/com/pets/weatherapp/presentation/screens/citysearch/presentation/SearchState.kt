package com.pets.weatherapp.presentation.screens.citysearch.presentation

sealed class SearchState {
    object Loading : SearchState()
    data class Success(val citiTitles: List<String>, val names: List<String>) : SearchState()
    data class Error(val error: Throwable) : SearchState()
}