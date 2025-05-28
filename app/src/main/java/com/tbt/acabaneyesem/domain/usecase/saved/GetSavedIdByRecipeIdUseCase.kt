package com.tbt.acabaneyesem.domain.usecase.saved

import com.tbt.acabaneyesem.domain.repository.saved.SavedRepository
import javax.inject.Inject

class GetSavedIdByRecipeIdUseCase @Inject constructor(
    private val savedRepository: SavedRepository
) {
    suspend operator fun invoke(recipeId: Int): Int? {
        return savedRepository.getSavedIdByRecipeId(recipeId)
    }
}