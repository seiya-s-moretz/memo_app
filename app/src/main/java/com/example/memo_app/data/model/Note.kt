package com.example.memo_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Note Room のテーブル定義を担う data class
 */

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val body: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
)