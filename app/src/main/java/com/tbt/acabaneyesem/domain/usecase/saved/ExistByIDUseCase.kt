package com.tbt.acabaneyesem.domain.usecase.saved

import com.tbt.acabaneyesem.domain.repository.saved.SavedRepository
import javax.inject.Inject

class ExistByIDUseCase @Inject constructor(
    private val savedRepository: SavedRepository
) {
    suspend operator fun invoke(recipeID: Int): Boolean {
        return savedRepository.existsById(recipeID)
    }
}