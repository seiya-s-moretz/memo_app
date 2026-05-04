package com.example.memo_app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.memo_app.data.local.NoteDatabase
import com.example.memo_app.repository.NoteRepository
import com.example.memo_app.ui.viewmodel.NoteEditViewModel

/**
 * NoteEditContainer
 * `NoteEditViewModel` を取得する
 * `noteId` を ViewModel に渡してメモデータをロードする
 * uiState の `isSaved` / `isDeleted` を監視し、完了時に画面遷移する
 * ViewModel のイベントを NoteEditScreen に橋渡しする
 */

@Composable
fun NoteEditContainer(
    noteId: Int?,
    onNavigateBack: () -> Unit,
) {

    val context = LocalContext.current
    val repository = NoteRepository(NoteDatabase.getDatabase(context).noteDao())
    val viewModel: NoteEditViewModel = viewModel(
        factory = NoteEditViewModel.provideFactory(repository)
    )
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(noteId) {
        viewModel.loadNote(noteId)
    }

    LaunchedEffect(uiState.isSaved, uiState.isDeleted) {
        if (uiState.isSaved || uiState.isDeleted) {
            onNavigateBack()
        }
    }

    NoteEditScreen(
        uiState = uiState,
        onTitleChange = viewModel::updateTitle,
        onBodyChange = viewModel::updateBody,
        onSaveClick = viewModel::saveNote,
        onDeleteClick = viewModel::showDeleteDialog,
        onDeleteConfirm = viewModel::deleteNote,
        onDeleteDismiss = viewModel::dismissDeleteDialog,
        onBackClick = onNavigateBack
    )

}