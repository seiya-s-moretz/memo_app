package com.example.memo_app.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.memo_app.ui.screen.components.DeleteConfirmDialog
import com.example.memo_app.ui.uistate.NoteEditUiState

/**
 * NoteEditScreen
 * 作成・編集画面のUIを描画する
 * 状態管理は一切行わない
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditScreen(
    uiState: NoteEditUiState,
    onTitleChange: (String) -> Unit,
    onBodyChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onDeleteConfirm: () -> Unit,
    onDeleteDismiss: () -> Unit,
    onBackClick: () -> Unit
) {
    val title = if (uiState.isNew) "新規作成" else "編集"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "戻る"
                        )
                    }
                }
            )
        }
    ){ padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ){
            TextField(
                value = uiState.title,
                onValueChange = onTitleChange,
                label = { Text("タイトル（必須）")},
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = uiState.body,
                onValueChange = onBodyChange,
                label = { Text( "本文")},
                modifier = Modifier.fillMaxWidth(),
                maxLines = 10
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onSaveClick,
                enabled = uiState.title.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ){
                Text("保存")
            }
            if (!uiState.isNew){
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text("削除", color= MaterialTheme.colorScheme.error)
                }
            }
        }

        if(uiState.showDeleteDialog){
            DeleteConfirmDialog(
                onConfirm = onDeleteConfirm,
                onDismiss = onDeleteDismiss
            )
        }
    }

}