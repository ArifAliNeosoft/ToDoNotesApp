package com.example.notesapplication.presentation.viewmodels

import android.app.Application
import android.graphics.Color.red
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.notesapplication.R
import com.example.notesapplication.data.model.NoteDataEntity
import com.example.notesapplication.data.model.NotePriority

class SharedViewModel(
    application: Application,
) : AndroidViewModel(application) {

    val isDatabaseEmpty: MutableLiveData<Boolean> = MutableLiveData(false)

    val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when (position) {
                0 -> (parent?.getChildAt(0) as TextView)
                    .setTextColor(ContextCompat.getColor(application, R.color.black))
                1 -> (parent?.getChildAt(0) as TextView)
                    .setTextColor(ContextCompat.getColor(application, R.color.purple_700))
                2 -> (parent?.getChildAt(0) as TextView)
                    .setTextColor(ContextCompat.getColor(application, androidx.transition.R.color.accent_material_dark))
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }

    fun checkIfDatabaseEmpty(noteData: List<NoteDataEntity>) {
        isDatabaseEmpty.value = noteData.isEmpty()
    }

    fun verifyDataFromUser(
        title: String,
        description: String,
    ): Boolean = !(title.isEmpty() || description.isEmpty())

    fun parsePriority(priority: String): NotePriority {
        return when (priority) {
            "High Priority" -> NotePriority.HIGH
            "Medium Priority" -> NotePriority.MEDIUM
            "Low Priority" -> NotePriority.LOW
            else -> NotePriority.NONE
        }
    }

}