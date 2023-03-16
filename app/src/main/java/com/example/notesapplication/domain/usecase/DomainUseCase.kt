package com.example.notesapplication.domain.usecase

data class DomainUseCase(
    val getAllNoteUseCase: GetAllNoteUseCase,
    val insertNoteUseCase: InsertNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val updateNoteUseCase: UpdateNoteUseCase,
)