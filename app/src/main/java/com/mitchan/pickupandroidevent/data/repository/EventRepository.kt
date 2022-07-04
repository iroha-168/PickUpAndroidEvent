package com.mitchan.pickupandroidevent.data.repository

import com.mitchan.pickupandroidevent.data.api.ConnpassApiClient
import com.mitchan.pickupandroidevent.data.entity.EventResponse

class EventRepository(
    private val connpassApiClient: ConnpassApiClient
) {
    suspend fun getEvent(): EventResponse {
        return connpassApiClient.getEvents().body()!!
    }
}