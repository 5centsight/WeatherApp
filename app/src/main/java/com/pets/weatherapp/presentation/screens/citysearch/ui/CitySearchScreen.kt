package com.pets.weatherapp.presentation.screens.citysearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import com.pets.weatherapp.presentation.screens.weather.presentation.ForecastViewModel
import com.pets.weatherapp.presentation.screens.citysearch.presentation.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitySearchScreen(
    viewModel: ForecastViewModel,
    searchViewModel: SearchViewModel,
    onBackClick: () -> Unit
) {
    val state by searchViewModel.uiState.collectAsState()

    var expanded by remember { mutableStateOf(true) }
    val textFieldState = rememberTextFieldState()

    val cities = state.citiTitles

    val filteredCities = if (textFieldState.text.isEmpty()) {
        emptyList()
    } else {
        cities.filter {
            it.contains(textFieldState.text, ignoreCase = true)
        }
    }

    SearchBar(
        modifier = Modifier
            .semantics { traversalIndex = 0f },
        inputField = {
            SearchBarDefaults.InputField(
                query = textFieldState.text.toString(),
                onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                onSearch = {},
                expanded = expanded,
                onExpandedChange = { expanded = it },
                placeholder = { Text("Поиск города...") },
                leadingIcon = {
                    IconButton(onClick = {
                        expanded = false
                        onBackClick()
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Назад")
                    }
                },
            )
        },
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(filteredCities) { city ->
                Card(
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    ListItem(
                        headlineContent = {
                            Text(
                                city,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        modifier = Modifier
                            .clickable {
                                viewModel.onCitySelected(city)
                                expanded = false
                                onBackClick()
                            }
                    )
                }
            }
        }
    }
}