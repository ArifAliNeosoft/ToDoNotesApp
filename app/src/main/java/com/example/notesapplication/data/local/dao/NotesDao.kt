package com.example.notesapplication.data.local.dao

import androidx.room.*
import com.example.notesapplication.data.model.NoteDataEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllData(): Flow<List<NoteDataEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(noteData: NoteDataEntity)

    @Update
    suspend fun updateData(noteData: NoteDataEntity)

    @Delete
    suspend fun deleteData(noteData: NoteDataEntity)

    @Query("DELETE FROM note_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM note_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<NoteDataEntity>>

    @Query("SELECT * FROM note_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END")
    fun sortByHighPriority(): Flow<List<NoteDataEntity>>

    @Query("SELECT * FROM note_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun sortByLowPriority(): Flow<List<NoteDataEntity>>
}