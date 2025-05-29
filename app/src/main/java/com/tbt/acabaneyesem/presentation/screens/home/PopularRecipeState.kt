package com.tbt.acabaneyesem.presentation.screens.home

import com.tbt.acabaneyesem.data.remote.recipie.popular.dto.RecipePopularDto

data class PopularRecipeState(
    val isLoading: Boolean = true,
    val popularRecipe: RecipePopularDto? = null,
    val error: String = ""
)
