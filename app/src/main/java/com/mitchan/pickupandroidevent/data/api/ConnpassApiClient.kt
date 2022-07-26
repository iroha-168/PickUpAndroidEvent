package com.mitchan.pickupandroidevent.data.api

import com.mitchan.pickupandroidevent.data.entity.EventResponse
import retrofit2.Response
import retrofit2.http.GET

interface ConnpassApiClient {
    @GET("/api/v1/event?keyword=android&count=100&order=2")
    suspend fun getEvents(): Response<EventResponse>
}