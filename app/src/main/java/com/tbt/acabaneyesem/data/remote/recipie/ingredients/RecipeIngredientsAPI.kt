package com.tbt.acabaneyesem.data.remote.recipie.ingredients

import com.tbt.acabaneyesem.data.remote.recipie.ingredients.dto.RecipeIngredientsDto
import retrofit2.http.GET

interface RecipeIngredientsAPI {
    @GET("malzemeler")
    suspend fun getIngredients(
    ): RecipeIngredientsDto
}