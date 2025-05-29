package com.tbt.acabaneyesem.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbt.acabaneyesem.domain.usecase.recipe.GetPopularRecipeUseCase
import com.tbt.acabaneyesem.domain.usecase.recipe.GetQuickRecipeUseCase
import com.tbt.acabaneyesem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularRecipeUseCase: GetPopularRecipeUseCase,
    private val getQuickRecipeUseCase: GetQuickRecipeUseCase
) : ViewModel() {

    init{
        getPopularRecipe()
        getQuickRecipe()
    }

    private val _popularRecipe = MutableStateFlow(PopularRecipeState())
    val popularRecipe: StateFlow<PopularRecipeState> = _popularRecipe.asStateFlow()

    private fun getPopularRecipe() {
        viewModelScope.launch {
            getPopularRecipeUseCase.getPopularRecipe().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _popularRecipe.value = _popularRecipe.value.copy(
                            isLoading = false,
                            error = resource.message ?: "Error"
                        )
                    }

                    is Resource.Loading -> {
                        _popularRecipe.value = _popularRecipe.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        _popularRecipe.value = _popularRecipe.value.copy(
                            isLoading = false,
                            error = "",
                            popularRecipe = resource.data
                        )
                    }
                }
            }
        }
    }

    private val _quickRecipe = MutableStateFlow(QuickRecipeState())
    val quickRecipe: StateFlow<QuickRecipeState> = _quickRecipe.asStateFlow()

    private fun getQuickRecipe() {
        viewModelScope.launch {
            getQuickRecipeUseCase.getQuickRecipe().collect { resource ->
                when (resource) {
                    is Resource.Error -> {
                        _quickRecipe.value = _quickRecipe.value.copy(
                            isLoading = false,
                            error = resource.message ?: "Error"
                        )
                    }

                    is Resource.Loading -> {
                        _quickRecipe.value = _quickRecipe.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }

                    is Resource.Success -> {
                        _quickRecipe.value = _quickRecipe.value.copy(
                            isLoading = false,
                            error = "",
                            quickRecipe = resource.data
                        )
                    }
                }
            }
        }
    }
}