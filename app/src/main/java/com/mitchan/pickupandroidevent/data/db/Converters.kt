package com.mitchan.pickupandroidevent.data.db

import androidx.room.TypeConverter
import com.mitchan.pickupandroidevent.data.entity.DateTime
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): DateTime? {
        return value?.let { DateTime(Date(it)) }
    }

    @TypeConverter
    fun dateToTimestamp(dateTime: DateTime?): Long? {
        return dateTime?.date?.time
    }
}