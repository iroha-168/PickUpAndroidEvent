package com.mitchan.pickupandroidevent.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mitchan.pickupandroidevent.data.entity.DateTime

@Entity
data class EventDto(
    @PrimaryKey
    val eventId: Long,
    val title: String,
    val eventUrl: String,
    val startedAt: DateTime?,
    val place: String?,
    val isFavorite: Boolean
)
