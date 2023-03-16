package com.example.notesapplication.common

import android.graphics.Color
import android.graphics.Color.red
import android.view.View
import android.widget.Spinner
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.example.notesapplication.R
import com.example.notesapplication.data.model.NoteDataEntity
import com.example.notesapplication.data.model.NotePriority
import com.example.notesapplication.presentation.fragments.AddFragmentDirections
import com.example.notesapplication.presentation.fragments.ListFragmentDirections
import com.example.notesapplication.presentation.fragments.UpdateFragmentDirections

import com.google.android.material.floatingactionbutton.FloatingActionButton
object BindingAdapter {

    @BindingAdapter("android:navigateToAddFragment")
    @JvmStatic
    fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean) {
        view.setOnClickListener {
            if (navigate) view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    @BindingAdapter("android:emptyDatabase")
    @JvmStatic
    fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>) {
        when (emptyDatabase.value) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.INVISIBLE
            else -> {  }
        }
    }

    @BindingAdapter("android:parsePriorityToInt")
    @JvmStatic
    fun parsePriorityToInt(view: Spinner, priority: NotePriority) {
        when (priority) {
            NotePriority.HIGH -> view.setSelection(0)
            NotePriority.MEDIUM -> view.setSelection(1)
            NotePriority.LOW -> view.setSelection(2)
            else -> NotePriority.NONE
        }
    }

    @BindingAdapter("android:parsePriorityColor")
    @JvmStatic
    fun parsePriorityColor(view: CardView, priority: NotePriority) {
        when (priority) {
            NotePriority.HIGH -> view.setCardBackgroundColor(view.context.getColor(androidx.appcompat.R.color.material_blue_grey_800))
            NotePriority.MEDIUM -> view.setCardBackgroundColor(view.context.getColor(com.google.android.material.R.color.material_divider_color))
            NotePriority.LOW -> view.setCardBackgroundColor(view.context.getColor(androidx.appcompat.R.color.accent_material_dark))
            else -> NotePriority.NONE
        }
    }
    @BindingAdapter("android:parseBackgroundColor")
    @JvmStatic
    fun parseBackgroundColor(view: ConstraintLayout, priority: NotePriority) {
        when (priority) {
            NotePriority.HIGH -> view.setBackgroundResource(ContextCompat.getColor(view.context, R.color.black))
            NotePriority.MEDIUM -> view.setBackgroundResource(ContextCompat.getColor(view.context, R.color.purple))
            NotePriority.LOW -> view.setBackgroundResource(ContextCompat.getColor(view.context, R.color.white))
            else -> NotePriority.NONE
        }
    }

    @BindingAdapter("android:sendDataToUpdateFragment")
    @JvmStatic
    fun sendDataToUpdateFragment(view: CardView, noteData: NoteDataEntity) {
        view.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(noteData)
            view.findNavController().navigate(action)
        }
    }

}