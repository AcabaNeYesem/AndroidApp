package com.tbt.acabaneyesem.di.feedback

import com.tbt.acabaneyesem.data.remote.feedback.FeedBackAPI
import com.tbt.acabaneyesem.data.repository.feedback.FeedBackRepositoryImpl
import com.tbt.acabaneyesem.domain.repository.feedback.FeedBackRepository
import com.tbt.acabaneyesem.util.Constant.BASE_API_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FeedBackModule {

    @Singleton
    @Provides
    fun provideFeedBackApi(): FeedBackAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FeedBackAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipeRepository(
        feedBackAPI: FeedBackAPI,
    ): FeedBackRepository {
        return FeedBackRepositoryImpl(
            feedBackAPI = feedBackAPI,
        )
    }
}