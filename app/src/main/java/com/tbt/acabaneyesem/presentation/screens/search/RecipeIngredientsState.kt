package com.tbt.acabaneyesem.presentation.screens.search

import com.tbt.acabaneyesem.data.remote.recipie.ingredients.dto.RecipeIngredientsDto

data class RecipeIngredientsState(
    val isLoading: Boolean = false,
    val ingredients: RecipeIngredientsDto? = null,
    val error: String = ""
)
