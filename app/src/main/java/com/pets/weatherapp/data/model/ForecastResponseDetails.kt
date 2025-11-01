package com.pets.weatherapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Metadata(
    @SerialName("resultset")
    val resultset: ResultSet
)

@Serializable
data class ResultSet(
    @SerialName("count")
    val count: Int = 0
)

@Serializable
data class WindDirection(
    @SerialName("title")
    val title: String = "",
    @SerialName("title_letter")
    val titleLetter: String = "",
    @SerialName("title_short")
    val titleShort: String = "",
    @SerialName("value")
    val value: String = "",
)

@Serializable
data class CoupledDetails(
    @SerialName("title")
    val title: String = "",
    @SerialName("value")
    val value: String = ""
)

@Serializable
data class AstronomyDetail(
    @SerialName("sunrise")
    val sunrise: String = "",
    @SerialName("sunset")
    val sunset: String = "",
    @SerialName("length_day_human")
    val lengthDay: String = ""
)

@Serializable
data class LinkCityName(
    @SerialName("city")
    val city: String = ""
)
