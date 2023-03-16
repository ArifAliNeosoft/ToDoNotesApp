package com.example.notesapplication.domain.repository

import com.example.notesapplication.data.model.NoteDataEntity
import kotlinx.coroutines.flow.Flow

interface INoteRepository {
    fun getAllNote(): Flow<List<NoteDataEntity>>
    fun searchNote(query: String): Flow<List<NoteDataEntity>>
    fun sortByHighPriority(): Flow<List<NoteDataEntity>>
    fun sortByLowPriority(): Flow<List<NoteDataEntity>>
    suspend fun insertNote(noteData: NoteDataEntity)
    suspend fun updateNote(noteData: NoteDataEntity)
    suspend fun deleteNote(noteData: NoteDataEntity)
    suspend fun deleteAllNote()
}