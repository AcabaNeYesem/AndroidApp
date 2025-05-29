package com.tbt.acabaneyesem.domain.usecase.image

import com.tbt.acabaneyesem.domain.repository.image.ImageRepository
import javax.inject.Inject

class DownloadImageUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend operator fun invoke(url: String): String? {
        return repository.downloadImage(url)
    }
}
