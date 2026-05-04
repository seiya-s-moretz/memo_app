package com.example.memo_app.ui.uistate

/**
 * NoteEditUiState
 */
data class NoteEditUiState(
    val id: Int? = null,
    val title: String = "",
    val body: String = "",
    val isNew: Boolean = true,
    val showDeleteDialog: Boolean = false,
    val isSaved: Boolean = false,
    val isDeleted: Boolean = false,
)
