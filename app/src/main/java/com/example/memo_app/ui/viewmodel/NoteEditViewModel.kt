package com.example.memo_app.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.memo_app.data.model.Note
import com.example.memo_app.repository.NoteRepository
import com.example.memo_app.ui.uistate.NoteEditUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * NoteEditViewModel
 * 編集対象メモのロード・保存・削除を担う
 * UiState を通じて UI に状態を公開する
 */

class NoteEditViewModel(
    private val repository: NoteRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NoteEditUiState())
    val uiState: StateFlow<NoteEditUiState> = _uiState

    /** メモの読み込み */
    fun loadNote(noteId: Int?) {
        if (noteId == null) {
            _uiState.update { it.copy(isNew = true) }
            return
        }

        viewModelScope.launch {
            val note = repository.getNoteById(noteId)
            if (note != null)
                _uiState.update {
                    it.copy(
                        id = note.id,
                        title = note.title,
                        body = note.body,
                        isNew = false,
                    )
                }
        }
    }

    /** タイトル更新 */
    fun updateTitle(newTitle: String) {
        _uiState.update { it.copy(title = newTitle) }
    }

    /** メモ内容更新 */
    fun updateBody(newBody: String) {
        _uiState.update { it.copy(body = newBody) }
    }

    /** メモ保存 */
    fun saveNote() {
        val current = _uiState.value
        if (current.title.isBlank()) return
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            if (current.isNew) {
                repository.insertNote(
                    Note(
                        title = current.title,
                        body = current.body,
                        createdAt = now,
                        updatedAt = now
                    )
                )
            } else {
                repository.updateNote(
                    Note(
                        id = current.id!!,
                        title = current.title,
                        body = current.body,
                        updatedAt = now
                    )
                )
            }
            _uiState.update { it.copy(isSaved = true) }
        }
    }

    /** 削除ダイアログ表示用 */
    fun showDeleteDialog() {
        _uiState.update { it.copy(showDeleteDialog = true) }
    }

    /** 削除ダイアログ却下用 */
    fun dismissDeleteDialog() {
        _uiState.update { it.copy(showDeleteDialog = false) }
    }

    /** メモ削除実行 */
    fun deleteNote() {
        val current = _uiState.value
        val id = current.id ?: return
        viewModelScope.launch {
            repository.deleteNoteById(id)
            _uiState.update { it.copy(isDeleted = true) }
        }
    }

    // Factory作成
    companion object{
        fun provideFactory(repository: NoteRepository): ViewModelProvider.Factory{
            return object: ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T{
                    @Suppress("UNCHECKED CAST")
                    return NoteEditViewModel(repository) as T
                }
            }
        }
    }
}