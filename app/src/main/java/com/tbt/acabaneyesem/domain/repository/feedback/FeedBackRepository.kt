package com.tbt.acabaneyesem.domain.repository.feedback

import com.tbt.acabaneyesem.data.remote.datasource.Feedback
import com.tbt.acabaneyesem.data.remote.feedback.dto.FeedBackDto
import retrofit2.http.Body

interface FeedBackRepository {
    suspend fun sendFeedBack(@Body feedback: Feedback) : FeedBackDto
}