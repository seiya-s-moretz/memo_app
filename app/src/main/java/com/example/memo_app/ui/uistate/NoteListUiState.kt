package com.example.memo_app.ui.uistate

import com.example.memo_app.data.model.Note

/**
 * NoteListUiState
 *
 * @param notes: List<Note>
 */
data class NoteListUiState(
    val notes: List<Note> = emptyList(),
    val isLoading: Boolean = false,
)
