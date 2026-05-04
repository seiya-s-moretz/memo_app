package com.example.memo_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.memo_app.repository.NoteRepository
import com.example.memo_app.ui.uistate.NoteListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * NoteListViewModel
 * メモ一覧を Repository から Flow で購読し、UiState に反映する
 * UIは UiState を読むだけでロジックを持たない
 */

class NoteListViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteListUiState())
    val uiState: StateFlow<NoteListUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getAllNotes().collect { notes ->
                _uiState.update { it.copy(notes = notes, isLoading = false) }
            }
        }
    }

    companion object{
        fun provideFactory(repository: NoteRepository): ViewModelProvider.Factory{
            return object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T{
                    @Suppress("UNCHECKED_CAST")
                    return NoteListViewModel(repository) as T
                }
            }
        }
    }
}