package com.tbt.acabaneyesem.domain.usecase.saved

import com.tbt.acabaneyesem.domain.repository.saved.SavedRepository
import javax.inject.Inject

class DeleteSavedByIDUseCase @Inject constructor(
    private val savedRepository: SavedRepository
){
    suspend operator fun invoke(id : Int) {
        savedRepository.deleteSavedById(id)
    }
}