package com.tbt.acabaneyesem.data.local.saved.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tbt.acabaneyesem.data.local.saved.Converters
import com.tbt.acabaneyesem.data.local.saved.dao.SavedDao
import com.tbt.acabaneyesem.data.local.saved.entity.Saved

@Database(entities = [Saved::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SavedDatabase : RoomDatabase() {

    abstract fun savedDao(): SavedDao

    companion object {
        @Volatile
        private var INSTANCE: SavedDatabase? = null

        fun getDatabase(context: Context): SavedDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SavedDatabase::class.java,
                    "saved_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}