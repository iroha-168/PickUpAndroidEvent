package com.mitchan.pickupandroidevent.di

import android.content.Context
import androidx.room.Room
import com.mitchan.pickupandroidevent.data.db.EventDao
import com.mitchan.pickupandroidevent.data.db.PickUpAndroidEventDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideEventDao(pickUpAndroidEventDatabase: PickUpAndroidEventDatabase): EventDao {
        return pickUpAndroidEventDatabase.eventDao()
    }

    @Provides
    @Singleton
    fun providePickUpEventDatabase(@ApplicationContext context: Context) : PickUpAndroidEventDatabase {
        return Room.databaseBuilder(
            context,
            PickUpAndroidEventDatabase::class.java,
            PickUpAndroidEventDatabase::class.java.name,
        ).build()
    }
}