package com.example.notesapplication.domain.usecase

import com.example.notesapplication.data.model.NoteDataEntity
import com.example.notesapplication.domain.repository.INoteRepository
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(private val iNoteRepository: INoteRepository) {
    suspend operator fun invoke(noteDataEntity: NoteDataEntity) =
        iNoteRepository.updateNote(noteDataEntity)
}