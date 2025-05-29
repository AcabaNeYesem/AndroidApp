package com.tbt.acabaneyesem.domain.repository.image

interface ImageRepository {
    suspend fun downloadImage(url: String): String?
}
