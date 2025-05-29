package com.tbt.acabaneyesem.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbt.acabaneyesem.data.local.saved.entity.Saved
import com.tbt.acabaneyesem.data.remote.datasource.Feedback
import com.tbt.acabaneyesem.domain.usecase.feedback.SendFeedBackUseCase
import com.tbt.acabaneyesem.domain.usecase.image.DownloadImageUseCase
import com.tbt.acabaneyesem.domain.usecase.saved.DeleteSavedByIDUseCase
import com.tbt.acabaneyesem.domain.usecase.saved.ExistByIDUseCase
import com.tbt.acabaneyesem.domain.usecase.saved.GetSavedIdByRecipeIdUseCase
import com.tbt.acabaneyesem.domain.usecase.saved.InsertSavedUseCase
import com.tbt.acabaneyesem.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val existByIDUseCase: ExistByIDUseCase,
    private val insertSavedUseCase: InsertSavedUseCase,
    private val deleteSavedByIDUseCase: DeleteSavedByIDUseCase,
    private val downloadImageUseCase: DownloadImageUseCase,
    private val getSavedIdByRecipeIdUseCase: GetSavedIdByRecipeIdUseCase,
    private val sendFeedBackUseCase: SendFeedBackUseCase
) : ViewModel() {

    private val _isSaved = MutableStateFlow(false)
    val isSaved: StateFlow<Boolean> = _isSaved

    fun checkIfSaved(recipeId: Int) {
        viewModelScope.launch {
            val result = existByIDUseCase.invoke(recipeId)
            _isSaved.value = result
        }
    }

    fun insertSaved(saved: Saved, imageUrl: String) {
        viewModelScope.launch {
            val localPath = downloadImageUseCase(imageUrl)
            localPath?.let {
                insertSavedUseCase.invoke(saved.copy(imagePath = it))
            }
            checkIfSaved(saved.recipeID)
        }
    }

    fun deleteSavedByID(id: Int, recipeId: Int) {
        viewModelScope.launch {
            deleteSavedByIDUseCase.invoke(id)
            checkIfSaved(recipeId)
        }
    }

    fun getSavedIDByRecipeID(recipeId: Int, onResult: (Int?) -> Unit) {
        viewModelScope.launch {
            val id = getSavedIdByRecipeIdUseCase.invoke(recipeId)
            onResult(id)
        }
    }

    private val _feedbackState = MutableStateFlow(FeedBackState())
    val feedbackState: StateFlow<FeedBackState> = _feedbackState.asStateFlow()

    fun sendFeedBack(feedback : Feedback){
        viewModelScope.launch {
            sendFeedBackUseCase.sendFeedback(feedback).collect { resource ->
                when(resource){
                    is Resource.Error<*> -> {
                        _feedbackState.value = _feedbackState.value.copy(
                            isLoading = false,
                            error = resource.message ?: "Bilinmeyen hata oluştu."
                        )
                    }
                    is Resource.Loading<*> -> {
                        _feedbackState.value = _feedbackState.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }
                    is Resource.Success<*> -> {
                        _feedbackState.value = _feedbackState.value.copy(
                            isLoading = false,
                            error = "",
                            response = resource.data
                        )
                    }
                }
            }
        }
    }
}
