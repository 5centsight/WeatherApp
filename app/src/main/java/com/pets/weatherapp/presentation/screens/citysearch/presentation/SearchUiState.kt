package com.pets.weatherapp.presentation.screens.citysearch.presentation

data class SearchUiState(
    val searchState: SearchState = SearchState.Loading,
    val citiNames: List<String> = emptyList(),
    val citiTitles: List<String> = emptyList()
)