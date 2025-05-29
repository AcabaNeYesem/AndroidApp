package com.tbt.acabaneyesem.presentation.screens.results

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbt.acabaneyesem.data.remote.datasource.TarifIstegi
import com.tbt.acabaneyesem.domain.model.RecipeDetails
import com.tbt.acabaneyesem.domain.usecase.recipe.GetSearchRecipesUseCase
import com.tbt.acabaneyesem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val searchRecipesUseCase: GetSearchRecipesUseCase,
): ViewModel(){

    private val _searchRecipes = MutableStateFlow(SearchRecipesState())
    val searchRecipes: StateFlow<SearchRecipesState> = _searchRecipes.asStateFlow()


    fun getSearchRecipe() {
        if (tarifIstegi.value != null) {
            viewModelScope.launch {
                searchRecipesUseCase.getSearchRecipes(tarifIstegi.value!!).collect { resource ->
                    when (resource) {
                        is Resource.Error -> {
                            _searchRecipes.value = _searchRecipes.value.copy(
                                isLoading = false,
                                error = resource.message ?: "Error"
                            )
                        }

                        is Resource.Loading -> {
                            _searchRecipes.value = _searchRecipes.value.copy(
                                isLoading = true,
                                error = ""
                            )
                        }

                        is Resource.Success -> {
                            _searchRecipes.value = _searchRecipes.value.copy(
                                isLoading = false,
                                error = "",
                                searchRecipe = resource.data
                            )
                        }
                    }
                }
            }
        }
    }

    private val _tarifIstegi = mutableStateOf<TarifIstegi?>(null)
    val tarifIstegi: State<TarifIstegi?> = _tarifIstegi

    fun setRecipe(tarifIstegi: TarifIstegi) {
        _tarifIstegi.value = tarifIstegi
    }
}