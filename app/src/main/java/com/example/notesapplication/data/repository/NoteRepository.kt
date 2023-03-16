package com.example.notesapplication.data.repository

import com.example.notesapplication.data.local.dao.NoteDao
import com.example.notesapplication.data.model.NoteDataEntity
import com.example.notesapplication.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) : INoteRepository {

    override fun getAllNote(): Flow<List<NoteDataEntity>> = noteDao.getAllData()

    override suspend fun insertNote(noteData: NoteDataEntity) = noteDao.insertData(noteData)
    override suspend fun updateNote(noteData: NoteDataEntity) = noteDao.updateData(noteData)
    override suspend fun deleteNote(noteData: NoteDataEntity) = noteDao.deleteData(noteData)


}