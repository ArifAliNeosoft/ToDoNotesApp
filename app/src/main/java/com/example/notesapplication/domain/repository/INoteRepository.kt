package com.example.notesapplication.domain.repository

import com.example.notesapplication.data.model.NoteDataEntity
import kotlinx.coroutines.flow.Flow

interface INoteRepository {
    fun getAllNote(): Flow<List<NoteDataEntity>>
    suspend fun insertNote(noteData: NoteDataEntity)
    suspend fun updateNote(noteData: NoteDataEntity)
    suspend fun deleteNote(noteData: NoteDataEntity)
}