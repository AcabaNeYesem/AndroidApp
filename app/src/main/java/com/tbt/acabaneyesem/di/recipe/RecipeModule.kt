package com.tbt.acabaneyesem.di.recipe

import com.tbt.acabaneyesem.data.remote.recipie.ingredients.RecipeIngredientsAPI
import com.tbt.acabaneyesem.data.remote.recipie.popular.RecipePopularAPI
import com.tbt.acabaneyesem.data.remote.recipie.quick.RecipeQuickAPI
import com.tbt.acabaneyesem.data.remote.recipie.search.RecipeSearchAPI
import com.tbt.acabaneyesem.data.repository.recipe.RecipeRepositoryImpl
import com.tbt.acabaneyesem.domain.repository.recipe.RecipeRepository
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
object RecipeModule {
    @Singleton
    @Provides
    fun provideQuickRecipeApi(): RecipeQuickAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeQuickAPI::class.java)
    }

    @Singleton
    @Provides
    fun providePopularRecipeApi(): RecipePopularAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipePopularAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchRecipesApi(): RecipeSearchAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeSearchAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipeIngredientsApi(): RecipeIngredientsAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeIngredientsAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeQuickAPI: RecipeQuickAPI,
        recipePopularAPI: RecipePopularAPI,
        recipeSearchAPI: RecipeSearchAPI,
        recipeIngredientsAPI: RecipeIngredientsAPI
    ): RecipeRepository {
        return RecipeRepositoryImpl(
            recipeQuickAPI = recipeQuickAPI,
            recipePopularAPI = recipePopularAPI,
            recipeSearchAPI = recipeSearchAPI,
            recipeIngredientsAPI = recipeIngredientsAPI
        )
    }
}