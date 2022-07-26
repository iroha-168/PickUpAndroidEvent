package com.mitchan.pickupandroidevent.data.repository

import com.mitchan.pickupandroidevent.data.api.ConnpassApiClient
import com.mitchan.pickupandroidevent.data.entity.Event
import dagger.Reusable
import javax.inject.Inject

@Reusable
class EventRepository @Inject constructor(
    private val connpassApiClient: ConnpassApiClient
) {
    suspend fun getEvent(): List<Event> {

        // TODO:titleに"kotlin"もしくは"Android"が含まれるデータだけをピックアップする
        val result = connpassApiClient.getEvents().body()!!
        return result.events.filter {
            it.title.contains(BIG_ANDROID)
                    || it.title.contains(SMALL_ANDROID)
                    || it.title.contains(BIG_KOTLIN)
                    || it.title.contains(SMALL_KOTLIN)
                    || it.title.contains(JETPACK_COMPOSE)
        }
    }

    companion object {
        private const val BIG_ANDROID = "Android"
        private const val SMALL_ANDROID = "android"
        private const val BIG_KOTLIN = "Kotlin"
        private const val SMALL_KOTLIN = "kotlin"
        private const val JETPACK_COMPOSE = "Jetpack Compose"
    }
}