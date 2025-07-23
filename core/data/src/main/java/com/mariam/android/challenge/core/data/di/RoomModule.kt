package com.mariam.android.challenge.core.data.di

import android.content.Context
import androidx.room.Room
import com.mariam.android.challenge.core.data.database.AppDatabase
import com.mariam.android.challenge.core.data.database.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "challenge_db",
        ).build()
    }

    @Provides
    fun provideItemDao(db: AppDatabase): ItemDao = db.itemDao()
}
