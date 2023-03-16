package com.example.notesapplication.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_table")
@Parcelize
data class NoteDataEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String,
    var priority: NotePriority,
) : Parcelable