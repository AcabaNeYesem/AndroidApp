package com.tbt.acabaneyesem.data.repository.feedback

import com.tbt.acabaneyesem.data.remote.datasource.Feedback
import com.tbt.acabaneyesem.data.remote.feedback.FeedBackAPI
import com.tbt.acabaneyesem.data.remote.feedback.dto.FeedBackDto
import com.tbt.acabaneyesem.domain.repository.feedback.FeedBackRepository
import javax.inject.Inject

class FeedBackRepositoryImpl @Inject constructor(
    private val feedBackAPI: FeedBackAPI
) : FeedBackRepository{
    override suspend fun sendFeedBack(feedback: Feedback): FeedBackDto {
        return feedBackAPI.sendFeedBack(feedback)
    }
}