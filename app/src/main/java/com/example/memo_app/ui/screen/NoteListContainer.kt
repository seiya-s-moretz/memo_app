package com.example.memo_app.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.memo_app.data.local.NoteDatabase
import com.example.memo_app.repository.NoteRepository
import com.example.memo_app.ui.viewmodel.NoteListViewModel

/**
 * NoteListContainer
 * `NoteListViewModel` を取得する
 * `uiState` を collectAsState() で購読する
 * ViewModel のイベントと NavController を NoteListScreen に橋渡しする
 */

@Composable
fun NoteListContainer(
    onNavigationToEdit: (Int?) -> Unit
) {
    // OSの窓口を取得
    val context = LocalContext.current
    // DB → DAO → Repositoryを作る
    val repository = NoteRepository(NoteDatabase.getDatabase(context).noteDao())
    // Repositoryを注入したViewModelを作る
    val viewModel: NoteListViewModel = viewModel(
        factory = NoteListViewModel.provideFactory(repository)
    )
    val uiState by viewModel.uiState.collectAsState()

    NoteListScreen(
        uiState = uiState,
        onFabClick = { onNavigationToEdit(null) },
        onNoteClick = { note -> onNavigationToEdit(note.id) }
    )
}