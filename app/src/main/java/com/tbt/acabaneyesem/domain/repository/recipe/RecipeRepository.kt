package com.tbt.acabaneyesem.domain.repository.recipe

import com.tbt.acabaneyesem.data.remote.datasource.TarifIstegi
import com.tbt.acabaneyesem.data.remote.recipie.ingredients.dto.RecipeIngredientsDto
import com.tbt.acabaneyesem.data.remote.recipie.popular.dto.RecipePopularDto
import com.tbt.acabaneyesem.data.remote.recipie.quick.dto.RecipeQuickDto
import com.tbt.acabaneyesem.data.remote.recipie.search.dto.RecipeSearchDto

interface RecipeRepository {
    suspend fun getPopularRecipe() : RecipePopularDto
    suspend fun getQuickRecipe() : RecipeQuickDto
    suspend fun getSearchRecipes(istek : TarifIstegi) : RecipeSearchDto
    suspend fun getIngredients(): RecipeIngredientsDto
}