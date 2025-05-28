package com.tbt.acabaneyesem.domain.usecase.recipe

import com.tbt.acabaneyesem.data.remote.datasource.TarifIstegi
import com.tbt.acabaneyesem.data.remote.recipie.quick.dto.RecipeQuickDto
import com.tbt.acabaneyesem.data.remote.recipie.search.dto.RecipeSearchDto
import com.tbt.acabaneyesem.domain.repository.recipe.RecipeRepository
import com.tbt.acabaneyesem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSearchRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
){
    fun getSearchRecipes(istek: TarifIstegi) : Flow<Resource<RecipeSearchDto>> = flow {
        try {
            val recipe = recipeRepository.getSearchRecipes(istek)
            if (recipe.isNotEmpty()) {
                emit(Resource.Success(recipe))
            } else {
                emit(Resource.Error("No Recipe Found"))
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