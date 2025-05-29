package com.tbt.acabaneyesem.domain.usecase.saved

import com.tbt.acabaneyesem.data.local.saved.entity.Saved
import com.tbt.acabaneyesem.domain.repository.saved.SavedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSavedUseCase @Inject constructor(
    private val savedRepository: SavedRepository
){
    operator fun invoke(): Flow<List<Saved>> {
        return savedRepository.getAllSaved()
    }
}