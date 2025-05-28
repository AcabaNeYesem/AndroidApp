package com.tbt.acabaneyesem.data.local.category

import com.tbt.acabaneyesem.domain.model.Category
import javax.inject.Inject

class LocalCategoryDataSource @Inject constructor() {
    fun getCategories(): List<Category> {
        return listOf(
            Category(1, "Aperatifler"),
            Category(2, "Bakliyat"),
            Category(3, "Bebekler İçin"),
            Category(4, "Çocuk Yemekleri"),
            Category(5, "Çorba Tarifleri"),
            Category(6, "Diğer Tarifler"),
            Category(7, "Diyet Tarifleri"),
            Category(8, "Dolma & Sarma"),
            Category(9, "Et Yemekleri"),
            Category(10, "Hızlı Yemekler"),
            Category(11, "İçecek Tarifleri"),
            Category(12, "Kahvaltılık Tarifler"),
            Category(13, "Makarna"),
            Category(14, "Pilav Tarifleri"),
            Category(15, "Salata & Meze & Kanepe"),
            Category(16, "Sandviç Tarifleri"),
            Category(17, "Sebze Yemekleri"),
            Category(18, "Tatlılar"),
            Category(19, "Yumurta Yemekleri")
        )
    }
}