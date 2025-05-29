package com.tbt.acabaneyesem.data.remote.feedback

import com.tbt.acabaneyesem.data.remote.datasource.Feedback
import com.tbt.acabaneyesem.data.remote.feedback.dto.FeedBackDto
import retrofit2.http.Body
import retrofit2.http.POST

interface FeedBackAPI {
    @POST("send-feedback")
    suspend fun sendFeedBack(@Body feedback: Feedback) : FeedBackDto
}