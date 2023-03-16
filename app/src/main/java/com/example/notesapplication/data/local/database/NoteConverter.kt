package com.example.notesapplication.data.local.database

import androidx.room.TypeConverter
import com.example.notesapplication.data.model.NotePriority

class NoteConverter {

    @TypeConverter
    fun fromPriority(notePriority: NotePriority): String {
        return notePriority.name
    }

    @TypeConverter
    fun toPriority(string: String): NotePriority {
        return NotePriority.valueOf(string)
    }

}