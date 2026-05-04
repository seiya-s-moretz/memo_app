package com.example.memo_app.repository

import com.example.memo_app.data.local.NoteDao
import com.example.memo_app.data.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * NoteRepository
 * DAO を抽象化し、ViewModel にデータ操作のインターフェースを提供する
 * UI / ViewModel 層が Room の実装詳細に依存しないようにする
 */

class NoteRepository(private val dao: NoteDao) {
    fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()

    suspend fun getNoteById(id: Int): Note? = dao.getNoteById(id)

    suspend fun insertNote(note: Note) = dao.insert(note)

    suspend fun updateNote(note: Note) = dao.update(note)

    suspend fun deleteNoteById(id: Int) = dao.deleteById(id)

}