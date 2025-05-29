package com.tbt.acabaneyesem.domain.repository.saved

import com.tbt.acabaneyesem.data.local.saved.entity.Saved
import kotlinx.coroutines.flow.Flow

interface SavedRepository {
    fun getAllSaved() : Flow<List<Saved>>
    suspend fun insertSaved(saved: Saved)
    suspend fun deleteSavedById(id:Int)
    suspend fun existsById(recipeID: Int): Boolean
    suspend fun getSavedIdByRecipeId(recipeId: Int): Int?
}