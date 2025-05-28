package com.tbt.acabaneyesem.data.repository.recipe

import com.tbt.acabaneyesem.data.remote.datasource.TarifIstegi
import com.tbt.acabaneyesem.data.remote.recipie.ingredients.RecipeIngredientsAPI
import com.tbt.acabaneyesem.data.remote.recipie.ingredients.dto.RecipeIngredientsDto
import com.tbt.acabaneyesem.data.remote.recipie.popular.RecipePopularAPI
import com.tbt.acabaneyesem.data.remote.recipie.popular.dto.RecipePopularDto
import com.tbt.acabaneyesem.data.remote.recipie.quick.RecipeQuickAPI
import com.tbt.acabaneyesem.data.remote.recipie.quick.dto.RecipeQuickDto
import com.tbt.acabaneyesem.data.remote.recipie.search.RecipeSearchAPI
import com.tbt.acabaneyesem.data.remote.recipie.search.dto.RecipeSearchDto
import com.tbt.acabaneyesem.domain.repository.recipe.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val recipeQuickAPI: RecipeQuickAPI,
    private val recipePopularAPI: RecipePopularAPI,
    private val recipeSearchAPI: RecipeSearchAPI,
    private val recipeIngredientsAPI: RecipeIngredientsAPI
) : RecipeRepository{
    override suspend fun getPopularRecipe(): RecipePopularDto {
        return recipePopularAPI.getPopularRecipe()
    }

    override suspend fun getQuickRecipe(): RecipeQuickDto {
        return recipeQuickAPI.getQuickRecipe()
    }

    override suspend fun getSearchRecipes(istek: TarifIstegi): RecipeSearchDto {
        return recipeSearchAPI.getSearchRecipes(istek)
    }

    override suspend fun getIngredients(): RecipeIngredientsDto {
        return recipeIngredientsAPI.getIngredients()
    }
}