package com.tbt.acabaneyesem.presentation.screens.details

import com.tbt.acabaneyesem.data.remote.feedback.dto.FeedBackDto

data class FeedBackState(
    val isLoading: Boolean = false,
    val response: FeedBackDto? = null,
    val error: String = ""
)
