package com.example.notesapplication.domain.usecase

data class DomainUseCase(
    val getAllNoteUseCase: GetAllNoteUseCase,
    val insertNoteUseCase: InsertNoteUseCase,
    val searchNoteUseCase: SearchNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val deleteAllNoteUseCase: DeleteAllNoteUseCase,
    val updateNoteUseCase: UpdateNoteUseCase,
    val sortByHighPriorityUseCase: SortByHighPriorityUseCase,
    val sortByLowPriorityUseCase: SortByLowPriorityUseCase,
)