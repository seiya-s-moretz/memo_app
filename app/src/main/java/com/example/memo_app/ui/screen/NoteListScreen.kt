package com.example.memo_app.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.memo_app.data.model.Note
import com.example.memo_app.ui.screen.components.EmptyView
import com.example.memo_app.ui.screen.components.NoteCard
import com.example.memo_app.ui.uistate.NoteListUiState

/**
 * NoteListScreen
 * 一覧画面のUIを描画する
 * メモ一覧・空状態・FABを表示する
 * 状態管理は一切行わない
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    uiState: NoteListUiState,
    onFabClick: () -> Unit,
    onNoteClick: (Note) -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("メモ一覧") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onFabClick) {
                Icon(Icons.Default.Add, contentDescription = "新規作成")
            }
        }
    ) { padding ->
        if (uiState.notes.isEmpty()) {
            EmptyView(modifier = Modifier.padding(padding))
        } else {
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(uiState.notes, key = { it.id }) { note ->
                    NoteCard(
                        note = note,
                        onClick = { onNoteClick(note) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NoteListScreenPreview() {
    val sampleNotes = listOf(
        Note(
            title = ""
        ),
    )

    val uiState = NoteListUiState(notes = sampleNotes)

    NoteListScreen(
        uiState,
        onFabClick = {},
        onNoteClick = {}
    )
}