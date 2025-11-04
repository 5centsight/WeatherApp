package com.pets.weatherapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CitiesResponse(
    @SerialName("metadata")
    val metadata: Metadata,
    @SerialName("cities")
    val cities: List<CityData>
)

@Serializable
data class CityData(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("region")
    val region: Int = 0,
    @SerialName("name")
    val name: String = "",
    @SerialName("title")
    val title: String = ""
)
