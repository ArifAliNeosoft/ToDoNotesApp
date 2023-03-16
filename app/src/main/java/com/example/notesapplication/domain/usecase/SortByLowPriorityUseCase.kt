package com.example.notesapplication.domain.usecase

import com.example.notesapplication.data.model.NoteDataEntity
import com.example.notesapplication.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SortByLowPriorityUseCase @Inject constructor(private val noteRepository: INoteRepository) {
    operator fun invoke(): Flow<List<NoteDataEntity>> = noteRepository.sortByLowPriority()
}