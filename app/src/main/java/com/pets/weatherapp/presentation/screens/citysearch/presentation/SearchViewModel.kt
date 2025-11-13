package com.pets.weatherapp.presentation.screens.citysearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pets.weatherapp.domain.repository.ForecastRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: ForecastRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<SearchUiState> = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCitiesData()
    }

    fun loadCitiesData() {
        viewModelScope.launch {
            try {
                val citiesTitles = repository.getCitiesTitlesList()
                val citiesNames = repository.getCitiesNamesList()
                _uiState.update {
                    it.copy(
                        citiTitles = citiesTitles,
                        searchState = SearchState.Success(citiesTitles, citiesNames)
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        searchState = SearchState.Error(e)
                    )
                }
            }
        }
    }
}