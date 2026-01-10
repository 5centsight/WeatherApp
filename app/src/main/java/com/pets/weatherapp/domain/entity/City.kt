package com.pets.weatherapp.domain.entity

import androidx.compose.runtime.Immutable

@Immutable
data class City(
    val name: String = "",
    val title: String = ""
)
