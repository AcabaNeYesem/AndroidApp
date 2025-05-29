package com.tbt.acabaneyesem.data.local.saved.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tbt.acabaneyesem.data.local.saved.entity.Saved
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDao {
    @Query("SELECT * FROM Saved")
    fun getAllSaved() : Flow<List<Saved>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSaved(saved: Saved)

    @Query("DELETE FROM Saved WHERE id = :id")
    suspend fun deleteSavedById(id:Int)

    @Query("SELECT EXISTS(SELECT 1 FROM Saved WHERE recipeId = :recipeId)")
    suspend fun existsById(recipeId: Int): Boolean

    @Query("SELECT id FROM Saved WHERE recipeId = :recipeId LIMIT 1")
    suspend fun getSavedIdByRecipeId(recipeId: Int): Int?
}