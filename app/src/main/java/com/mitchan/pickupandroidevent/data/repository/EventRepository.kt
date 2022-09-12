package com.mitchan.pickupandroidevent.data.repository

import com.mitchan.pickupandroidevent.data.api.ConnpassApiClient
import com.mitchan.pickupandroidevent.data.db.EventDao
import com.mitchan.pickupandroidevent.data.db.EventDto
import dagger.Reusable
import javax.inject.Inject

@Reusable
class EventRepository @Inject constructor(
    private val connpassApiClient: ConnpassApiClient,
    private val eventDao: EventDao
) {
    val events = eventDao.getAll()
    val favoriteEvents = eventDao.getFavoriteEvents()

    suspend fun refresh() {
        val result = connpassApiClient.getEvents().body()!!
        val events = result.events.filter {
            it.title.contains(BIG_ANDROID)
                    || it.title.contains(SMALL_ANDROID)
                    || it.title.contains(BIG_KOTLIN)
                    || it.title.contains(SMALL_KOTLIN)
                    || it.title.contains(JETPACK_COMPOSE)
        }.map {
            EventDto(
                eventId = it.eventId,
                eventUrl = it.eventUrl,
                title = it.title,
                startedAt = it.startedAt,
                place = it.place,
                isFavorite = false
            )
        }
        upsert(events)
    }

    private suspend fun upsert(events: List<EventDto>) {
        events.forEach { event ->
            if (eventDao.insert(event) == -1L) {
                eventDao.update(
                    eventId = event.eventId,
                    eventUrl = event.eventUrl,
                    title = event.title,
                    startedAt = event.startedAt,
                    place = event.place
                )
            }
        }
    }

    suspend fun updateEvent(event: EventDto) {
        eventDao.update(event)
    }

    companion object {
        private const val BIG_ANDROID = "Android"
        private const val SMALL_ANDROID = "android"
        private const val BIG_KOTLIN = "Kotlin"
        private const val SMALL_KOTLIN = "kotlin"
        private const val JETPACK_COMPOSE = "Jetpack Compose"
    }
}