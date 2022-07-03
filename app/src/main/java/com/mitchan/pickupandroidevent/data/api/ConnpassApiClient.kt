package com.mitchan.pickupandroidevent.data.api

import com.mitchan.pickupandroidevent.data.entity.EventResponse
import retrofit2.Response
import retrofit2.http.GET

interface ConnpassApiClient {
    @GET("/api/v1/event?keyword=android")
    suspend fun getEvents(): Response<EventResponse>
}