package com.tbt.acabaneyesem.data.remote.recipie.search

import com.tbt.acabaneyesem.data.remote.datasource.TarifIstegi
import com.tbt.acabaneyesem.data.remote.recipie.search.dto.RecipeSearchDto
import retrofit2.http.Body
import retrofit2.http.POST

interface RecipeSearchAPI {
    @POST("tarif-oner")
    suspend fun getSearchRecipes(@Body istek: TarifIstegi) : RecipeSearchDto
}