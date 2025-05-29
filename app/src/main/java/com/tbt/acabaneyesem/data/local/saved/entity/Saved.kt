package com.tbt.acabaneyesem.data.local.saved.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Saved(
    @PrimaryKey(true)
    val id : Int = 0,
    val recipeID : Int,
    val title : String,
    val servings : String,
    val cookTime : String,
    val prepTime : String,
    val ingredients : List<String>,
    val instruction : List<String>,
    val category : String,
    val imagePath : String
)