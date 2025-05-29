package com.tbt.acabaneyesem.data.repository.saved

import com.tbt.acabaneyesem.data.local.saved.dao.SavedDao
import com.tbt.acabaneyesem.data.local.saved.entity.Saved
import com.tbt.acabaneyesem.domain.repository.saved.SavedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SavedRepositoryImpl @Inject constructor(
    private val savedDao: SavedDao
) : SavedRepository {
    override fun getAllSaved(): Flow<List<Saved>> {
        return savedDao.getAllSaved()
    }

    override suspend fun insertSaved(saved: Saved) {
        savedDao.insertSaved(saved)
    }

    override suspend fun deleteSavedById(id: Int) {
        savedDao.deleteSavedById(id)
    }

    override suspend fun existsById(recipeID: Int): Boolean {
        return savedDao.existsById(recipeID)
    }

    override suspend fun getSavedIdByRecipeId(recipeId: Int): Int? {
        return savedDao.getSavedIdByRecipeId(recipeId)
    }
}