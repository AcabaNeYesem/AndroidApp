package com.tbt.acabaneyesem.domain.usecase.category

import com.tbt.acabaneyesem.domain.model.Category
import com.tbt.acabaneyesem.domain.repository.category.CategoryRepository
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): List<Category> {
        return categoryRepository.getCategories()
    }
}