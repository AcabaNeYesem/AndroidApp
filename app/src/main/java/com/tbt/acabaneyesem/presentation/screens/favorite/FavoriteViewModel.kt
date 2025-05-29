package com.tbt.acabaneyesem.presentation.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbt.acabaneyesem.data.local.saved.entity.Saved
import com.tbt.acabaneyesem.domain.usecase.saved.DeleteSavedByIDUseCase
import com.tbt.acabaneyesem.domain.usecase.saved.GetAllSavedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getAllSavedUseCase: GetAllSavedUseCase,
    private val deleteSavedByIDUseCase: DeleteSavedByIDUseCase
) : ViewModel(){

    init {
        getSaved()
    }

    private val _allSaved = MutableStateFlow<List<Saved>>(emptyList())
    val allSaved : StateFlow<List<Saved>> = _allSaved

    private fun getSaved(){
        viewModelScope.launch {
            getAllSavedUseCase.invoke().collect{
                _allSaved.value = it
            }
        }
    }

    fun deleteSavedByID(id: Int) {
        viewModelScope.launch {
            deleteSavedByIDUseCase.invoke(id)
        }
    }

    private fun deleteImageFromStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
