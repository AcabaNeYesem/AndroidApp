package com.tbt.acabaneyesem.data.remote.recipie.popular

import com.tbt.acabaneyesem.data.remote.recipie.popular.dto.RecipePopularDto
import retrofit2.http.GET

interface RecipePopularAPI {
    @GET("populer")
    suspend fun getPopularRecipe(
    ): RecipePopularDto
}