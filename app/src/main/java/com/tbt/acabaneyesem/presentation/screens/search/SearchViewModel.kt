package com.tbt.acabaneyesem.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbt.acabaneyesem.domain.model.Category
import com.tbt.acabaneyesem.domain.usecase.category.CategoryUseCase
import com.tbt.acabaneyesem.domain.usecase.recipe.GetRecipeIngredientsUseCase
import com.tbt.acabaneyesem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
    private val getRecipeIngredientsUseCase: GetRecipeIngredientsUseCase
): ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories = _categories.asStateFlow()

    init {
        _categories.value = categoryUseCase.invoke()
        getRecipeIngredients()
    }

    private val _recipeIngredients = MutableStateFlow(RecipeIngredientsState())
    val recipeIngredients: StateFlow<RecipeIngredientsState> = _recipeIngredients.asStateFlow()

    fun getRecipeIngredients() {
        viewModelScope.launch {
            getRecipeIngredientsUseCase.getRecipeIngredients().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _recipeIngredients.value = _recipeIngredients.value.copy(
                            isLoading = false,
                            error = resource.message ?: "Error"
                        )
                    }

                    is Resource.Loading -> {
                        _recipeIngredients.value = _recipeIngredients.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        _recipeIngredients.value = _recipeIngredients.value.copy(
                            isLoading = false,
                            error = "",
                            ingredients = resource.data
                        )
                    }
                }
            }
        }
    }


}
