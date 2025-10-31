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
    @SerialName("pressure")
    val pressure: Int = 0,
    @SerialName("humidity")
    val humidity: Int = 0,
    @SerialName("cloud")
    val cloud: ForecastDetail,
    @SerialName("wind")
    val wind: CloudDetail,
    @SerialName("precipitation")
    val precipitation: ForecastDetail,
    @SerialName("astronomy")
    val astronomy: AstronomyDetail,
    @SerialName("icon")
    val iconName: String
)

@Serializable
data class CloudDetail(
    @SerialName("speed")
    val speed: Int = 0,
    @SerialName("direction")
    val direction: CloudDirection
)

@Serializable
data class CloudDirection(
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
data class ForecastDetail(
    @SerialName("title")
    val title: String = "",
    @SerialName("value")
    val value: String = ""
)

@Serializable
data class AstronomyDetail(
    @SerialName("sunrize")
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

