package com.pets.weatherapp.data.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Locale

class InstantSerializer : KSerializer<Instant> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("Instant", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Instant {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd\'T\'HH:mm:ssXX")
        val dateString = decoder.decodeString()
        return Instant.from(formatter.parse(dateString))

    }

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString(value.toString())
    }

    companion object {
        val WEEK_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern(
            "EE, dd MMMM", Locale("ru")
        )
    }
}