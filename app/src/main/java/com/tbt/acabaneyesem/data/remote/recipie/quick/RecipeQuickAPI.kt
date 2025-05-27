package com.tbt.acabaneyesem.data.remote.recipie.quick

import com.tbt.acabaneyesem.data.remote.recipie.quick.dto.RecipeQuickDto
import retrofit2.http.GET

interface RecipeQuickAPI {
    @GET("quick")
    suspend fun getQuickRecipe(
    ): RecipeQuickDto
}