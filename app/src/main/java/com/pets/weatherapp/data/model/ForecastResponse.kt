package com.pets.weatherapp.data.model

import com.pets.weatherapp.domain.mapper.InstantSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class ForecastResponse(
    @SerialName("metadata")
    val metadata: Metadata,
    @SerialName("forecasts")
    val forecasts: List<Forecast>
)

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
data class Forecast(
    @SerialName("links")
    val links: LinkCityName,
    @Serializable(with = InstantSerializer::class)
    @SerialName("date")
    val date: Instant,
    @SerialName("update_date")
    @Serializable(with = InstantSerializer::class)
    val updateDate: Instant,
    @SerialName("temperature")
    val temperature: Int = 0,
    @SerialName("feel_like_temperature")
    val feelLikeTemp: Int = 0,
    @SerialName("humidity")
    val humidity: Int = 0,
    @SerialName("cloud")
    val cloud: ForecastDetail,
    @SerialName("precipitation")
    val precipitation: ForecastDetail
)

@Serializable
data class ForecastDetail(
    @SerialName("title")
    val title: String = "",
    @SerialName("value")
    val value: String = ""
)

@Serializable
data class LinkCityName(
    @SerialName("city")
    val city: String = ""
)

