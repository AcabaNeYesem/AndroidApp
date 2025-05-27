package com.tbt.acabaneyesem.data.remote.recipie.search.dto

data class RecipeSearchDtoItem(
    val baslik: String,
    val hazirlik_süresi: String,
    val id: Int,
    val kac_kisilik: Int,
    val kategori: String,
    val malzemeler: List<String>,
    val pisirme_süresi: String,
    val url: String,
    val yapilis: List<String>
)