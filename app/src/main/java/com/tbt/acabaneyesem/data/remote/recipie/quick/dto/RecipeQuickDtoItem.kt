package com.tbt.acabaneyesem.data.remote.recipie.quick.dto

data class RecipeQuickDtoItem(
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