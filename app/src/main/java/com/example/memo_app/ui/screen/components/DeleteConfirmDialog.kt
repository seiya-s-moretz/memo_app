package com.example.memo_app.ui.screen.components

import android.R.attr.divider
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.memo_app.R

/**
 * DeleteConfirmDialog
 * 削除前の確認ダイアログを表示する
 * 「削除する」「キャンセル」の2択を提供する
 */

@Composable
fun DeleteConfirmDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        dismissButton = {},
        text = {
            Column {
                Text(
                    stringResource(R.string.dialog_01),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    stringResource(R.string.dialog_02),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                )
                Spacer(modifier = Modifier.height(30.dp))
                Divider()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onDismiss() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("キャンセル", style = MaterialTheme.typography.labelLarge)

                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(Color(0xFFFFEEEE))
                            .clickable { onConfirm() },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("OK", style = MaterialTheme.typography.labelLarge)

                    }

                }
            }
        }

    )
//    AlertDialog(
//        onDismissRequest = onDismiss,
//        title = {
//            Text(
//                stringResource(R.string.dialog_01),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentWidth(Alignment.CenterHorizontally)
//            )
//        },
//        text = {
//            Text(
//                stringResource(R.string.dialog_02),
//                color = MaterialTheme.colorScheme.error,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentWidth(Alignment.CenterHorizontally)
//            )
//        },
//        confirmButton = {
//            TextButton(onClick = onConfirm) {
//                Text("OK", fontSize = 20.sp, color = MaterialTheme.colorScheme.primary)
//            }
//        },
//        dismissButton = {
//            TextButton(onClick = onDismiss) {
//                Text(
//                    stringResource(R.string.cancel),
//                    fontSize = 20.sp,
//                    color = MaterialTheme.colorScheme.secondary
//                )
//            }
//        }
//
//    )

}

@Preview
@Composable
fun DialogPreview() {
    DeleteConfirmDialog(
        onConfirm = {},
        onDismiss = {},
    )
}