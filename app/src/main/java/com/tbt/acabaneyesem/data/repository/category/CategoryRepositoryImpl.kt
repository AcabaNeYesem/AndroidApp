package com.tbt.acabaneyesem.data.repository.category

import com.tbt.acabaneyesem.data.local.category.LocalCategoryDataSource
import com.tbt.acabaneyesem.domain.model.Category
import com.tbt.acabaneyesem.domain.repository.category.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val localCategoryDataSource: LocalCategoryDataSource
) : CategoryRepository {
    override fun getCategories(): List<Category> {
        return localCategoryDataSource.getCategories()
    }
}