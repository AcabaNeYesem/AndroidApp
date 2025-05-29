package com.tbt.acabaneyesem.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tbt.acabaneyesem.domain.model.RecipeDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeSharedViewModel @Inject constructor() : ViewModel(){

    private val _selectedRecipe = mutableStateOf<RecipeDetails?>(null)
    val selectedRecipe: State<RecipeDetails?> = _selectedRecipe

    fun setRecipe(recipe: RecipeDetails) {
        _selectedRecipe.value = recipe
    }
}