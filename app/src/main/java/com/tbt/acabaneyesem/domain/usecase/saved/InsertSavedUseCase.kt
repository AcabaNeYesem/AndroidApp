package com.tbt.acabaneyesem.domain.usecase.saved

import com.tbt.acabaneyesem.data.local.saved.entity.Saved
import com.tbt.acabaneyesem.domain.repository.saved.SavedRepository
import javax.inject.Inject

class InsertSavedUseCase @Inject constructor(
    private val savedRepository: SavedRepository
){
    suspend operator fun invoke(saved : Saved) {
        savedRepository.insertSaved(saved)
    }
}