package com.mitchan.pickupandroidevent.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.mitchan.pickupandroidevent.data.entity.DateTime
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert
    suspend fun insertAll(vararg event: EventDto)

    @Insert
    suspend fun insert(event: EventDto): Long

    @Update
    suspend fun update(event: EventDto)

    @Query("UPDATE EventDto SET eventId = :eventId, title = :title, eventUrl = :eventUrl, startedAt = :startedAt, place = :place ")
    suspend fun update(
        eventId: Long,
        title: String,
        eventUrl: String,
        startedAt: DateTime?,
        place: String?
    )

    @Query("SELECT * FROM EventDto WHERE isFavorite = 1")
    fun getFavoriteEvents(): Flow<List<EventDto>>

    @Query("SELECT * FROM EventDto")
    fun getAll(): Flow<List<EventDto>>
}