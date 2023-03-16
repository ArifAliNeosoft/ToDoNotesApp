package com.example.notesapplication.di

import android.content.Context
import androidx.room.Room
import com.example.notesapplication.common.Constants.NOTE_DB_NAME
import com.example.notesapplication.data.local.dao.NoteDao
import com.example.notesapplication.data.local.database.NoteDatabase
import com.example.notesapplication.data.repository.NoteRepository
import com.example.notesapplication.domain.repository.INoteRepository
import com.example.notesapplication.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        NOTE_DB_NAME
    ).build()

    @Singleton
    @Provides
    fun providesNoteDao(database: NoteDatabase) = database.noteDao()

    @Singleton
    @Provides
    fun providesRepository(noteDao: NoteDao): INoteRepository = NoteRepository(noteDao)

    @Singleton
    @Provides
    fun providesUseCase(repository: INoteRepository): DomainUseCase {
        return DomainUseCase(
            getAllNoteUseCase = GetAllNoteUseCase(repository),
            insertNoteUseCase = InsertNoteUseCase(repository),
            searchNoteUseCase = SearchNoteUseCase(repository),
            deleteAllNoteUseCase = DeleteAllNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            updateNoteUseCase = UpdateNoteUseCase(repository),
            sortByHighPriorityUseCase = SortByHighPriorityUseCase(repository),
            sortByLowPriorityUseCase = SortByLowPriorityUseCase(repository)
        )
    }

}