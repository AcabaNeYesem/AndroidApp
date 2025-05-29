package com.tbt.acabaneyesem.presentation.screens.home

import com.tbt.acabaneyesem.data.remote.recipie.quick.dto.RecipeQuickDto

data class QuickRecipeState (
    val isLoading: Boolean = true,
    val quickRecipe: RecipeQuickDto? = null,
    val error: String = ""
)