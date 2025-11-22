package com.pets.weatherapp.data.dto

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class SerializationConverters {

    val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        encodeDefaults = true
        isLenient = true
    }

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd\'T\'HH:mm:ssXX")

    @TypeConverter
    fun fromInstant(instant: Instant): String {
        return formatter.format(instant.atZone(ZoneId.systemDefault()))
    }

    @TypeConverter
    fun toInstant(value: String?): Instant {
        return ZonedDateTime.parse(value, formatter).toInstant()
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return json.encodeToString(ListSerializer(String.serializer()), list)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return if (value.isBlank()) emptyList()
        else json.decodeFromString(ListSerializer(String.serializer()), value)
    }

    @TypeConverter
    fun fromIntList(list: List<Int>): String {
        return json.encodeToString(ListSerializer(Int.serializer()), list)
    }

    @TypeConverter
    fun toIntList(value: String): List<Int> {
        return if (value.isBlank()) emptyList()
        else json.decodeFromString(ListSerializer(Int.serializer()), value)
    }
}