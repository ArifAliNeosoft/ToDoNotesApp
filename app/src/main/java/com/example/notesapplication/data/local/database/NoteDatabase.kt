package com.example.notesapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesapplication.data.local.dao.NoteDao
import com.example.notesapplication.data.model.NoteDataEntity

@Database(entities = [NoteDataEntity::class], version = 1, exportSchema = false)
@TypeConverters(NoteConverter::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}