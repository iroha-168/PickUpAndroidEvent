package com.mitchan.pickupandroidevent.data.entity

import java.util.*

data class EventResponse (
    val events: List<Event>
)

data class Event(
    val eventId: Long,
    val title: String,
    val eventUrl: String,
    val startedAt: Date,
    val place: String,
)