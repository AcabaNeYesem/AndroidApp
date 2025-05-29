package com.tbt.acabaneyesem.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

object Constant {
    const val BASE_API_URL = "https://fastapi-tarif-1016492826599.europe-west1.run.app/"

    @Composable
    fun screenHeight(): Int = LocalConfiguration.current.screenHeightDp

    @Composable
    fun screenWidth(): Int = LocalConfiguration.current.screenWidthDp
}