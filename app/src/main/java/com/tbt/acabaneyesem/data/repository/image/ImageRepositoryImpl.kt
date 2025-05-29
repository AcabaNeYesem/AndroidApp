package com.tbt.acabaneyesem.data.repository.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.tbt.acabaneyesem.domain.repository.image.ImageRepository
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val context: Context
) : ImageRepository {

    override suspend fun downloadImage(url: String): String? {
        return try {
            val request = ImageRequest.Builder(context)
                .data(url)
                .allowHardware(false)
                .build()
            val result = ImageLoader(context).execute(request) as? SuccessResult ?: return null
            val bitmap = (result.drawable as BitmapDrawable).bitmap

            val file = File(context.cacheDir, "img_${System.currentTimeMillis()}.png")
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
            }

            file.absolutePath
        } catch (e: Exception) {
            null
        }
    }
}
