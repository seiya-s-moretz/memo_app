package com.example.memo_app.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * DateFormatter
 * NoteCard でメモの更新日時を表示する際に使用する拡張関数。
 */

fun Long.toFormattedDate(): String {
    val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault())
    return sdf.format(Date(this))
}