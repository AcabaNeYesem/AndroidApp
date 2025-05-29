package com.tbt.acabaneyesem.presentation.screens.results

import com.tbt.acabaneyesem.data.remote.recipie.search.dto.RecipeSearchDto

data class SearchRecipesState(
    val isLoading: Boolean = true,
    val searchRecipe: RecipeSearchDto? = null,
    val error: String = ""
)
