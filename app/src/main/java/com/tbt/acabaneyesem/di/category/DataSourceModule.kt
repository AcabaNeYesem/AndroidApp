package com.tbt.acabaneyesem.di.category

import com.tbt.acabaneyesem.data.repository.category.CategoryRepositoryImpl
import com.tbt.acabaneyesem.domain.repository.category.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

}