package com.mitchan.pickupandroidevent.data.entity

import android.annotation.SuppressLint
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class EventResponse (
    val events: List<Event>
)

@Serializable
data class Event(
    @SerialName("event_id")
    val eventId: Long,
    val title: String,
    @SerialName("event_url")
    val eventUrl: String,
    @SerialName("started_at")
    @Contextual
    val startedAt: DateTime?,
    val place: String?,
)

@Serializable(with = DateTimeSerializer::class)
data class DateTime(val date: Date)

@Serializer(forClass = Date::class)
object DateTimeSerializer: KSerializer<DateTime> {
    @SuppressLint("SimpleDateFormat")
    private val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DateSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DateTime) {
        encoder.encodeString(df.format(value.date))
    }

    override fun deserialize(decoder: Decoder): DateTime {
        return DateTime(df.parse(decoder.decodeString()))
    }
}