package com.tbt.acabaneyesem.di.saved

import com.tbt.acabaneyesem.data.repository.saved.SavedRepositoryImpl
import com.tbt.acabaneyesem.domain.repository.saved.SavedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SavedRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSavedRepository(
        savedRepositoryImpl: SavedRepositoryImpl
    ): SavedRepository
}