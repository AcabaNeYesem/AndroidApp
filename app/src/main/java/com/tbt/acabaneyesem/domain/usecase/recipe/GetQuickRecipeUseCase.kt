package com.tbt.acabaneyesem.domain.usecase.recipe

import com.tbt.acabaneyesem.data.remote.recipie.quick.dto.RecipeQuickDto
import com.tbt.acabaneyesem.domain.repository.recipe.RecipeRepository
import com.tbt.acabaneyesem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetQuickRecipeUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
){
    fun getQuickRecipe() : Flow<Resource<RecipeQuickDto>> = flow {
        try {
            val recipe = recipeRepository.getQuickRecipe()
            if (recipe.isNotEmpty()) {
                emit(Resource.Success(recipe))
            } else {
                emit(Resource.Error("No Quick Recipe Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error : ${e}"))
        } catch (e: IOException) {
            emit(Resource.Error("Error : ${e}"))
        } catch (e: Exception) {
            emit(Resource.Error("Error : ${e}"))
        }
    }
}