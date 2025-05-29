package com.tbt.acabaneyesem.di.saved

import android.content.Context
import androidx.room.Room
import com.tbt.acabaneyesem.data.local.saved.dao.SavedDao
import com.tbt.acabaneyesem.data.local.saved.database.SavedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SavedDatabaseModule {

    @Singleton
    @Provides
    fun provideAssetsDatabase(@ApplicationContext context: Context): SavedDatabase {
        return Room.databaseBuilder(
            context,
            SavedDatabase::class.java,
            "saved_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideAssetsDao(savedDatabase: SavedDatabase): SavedDao {
        return savedDatabase.savedDao()
    }
}