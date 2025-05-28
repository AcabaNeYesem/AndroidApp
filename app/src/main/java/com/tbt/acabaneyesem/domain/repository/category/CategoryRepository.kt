package com.tbt.acabaneyesem.domain.repository.category

import com.tbt.acabaneyesem.domain.model.Category

interface CategoryRepository {
    fun getCategories(): List<Category>
}