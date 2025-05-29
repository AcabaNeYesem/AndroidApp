package com.tbt.acabaneyesem.domain.usecase.feedback

import com.tbt.acabaneyesem.data.remote.datasource.Feedback
import com.tbt.acabaneyesem.data.remote.feedback.dto.FeedBackDto
import com.tbt.acabaneyesem.domain.repository.feedback.FeedBackRepository
import com.tbt.acabaneyesem.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SendFeedBackUseCase @Inject constructor(
    private val feedBackRepository: FeedBackRepository
) {
    fun sendFeedback(feedback: Feedback) : Flow<Resource<FeedBackDto>> = flow {
        try {
            val feedback = feedBackRepository.sendFeedBack(feedback)
            if (feedback.message == "success") {
                emit(Resource.Success(feedback))
            } else {
                emit(Resource.Error("No Recipe Found"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("Error : ${e}"))
        } catch (e: IOException) {
            emit(Resource.Error("Error : ${e}"))
        } catch (e: Exception) {
            emit(Resource.Error("Error : ${e}"))
        }
    }
}