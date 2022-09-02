package com.mitchan.pickupandroidevent.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mitchan.pickupandroidevent.data.entity.Event

@Database(entities = [EventDto::class], version = 1)
@TypeConverters(Converters::class)
abstract class PickUpAndroidEventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}