package com.mitchan.pickupandroidevent.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mitchan.pickupandroidevent.data.api.ConnpassApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    private val json = Json { ignoreUnknownKeys = true }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideConnpassApiClient() : ConnpassApiClient {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://connpass.com")
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ConnpassApiClient::class.java)
    }
}