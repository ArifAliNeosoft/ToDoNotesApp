package com.example.notesapplication.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapplication.data.model.NoteDataEntity
import com.example.notesapplication.domain.usecase.DomainUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NoteViewModel @Inject constructor(
    private val useCase: DomainUseCase,
) : ViewModel() {

    private val _getAllData = MutableLiveData<List<NoteDataEntity>>()
    val getAllData: LiveData<List<NoteDataEntity>> = _getAllData

    private val _searchNote = MutableLiveData<List<NoteDataEntity>>()
    val searchNote: LiveData<List<NoteDataEntity>> = _searchNote

    private var _sortByHighPriority = MutableLiveData<List<NoteDataEntity>>()
    val sortByHighPriority: LiveData<List<NoteDataEntity>> = _sortByHighPriority

    private var _sortByLowPriority = MutableLiveData<List<NoteDataEntity>>()
    val sortByLowPriority: LiveData<List<NoteDataEntity>> = _sortByLowPriority

    init {
        sortHighPriority()
        sortLowPriority()
    }

    private fun sortHighPriority() {
        viewModelScope.launch {
            useCase.sortByHighPriorityUseCase.invoke().collectLatest {
                _sortByHighPriority.value = it
            }
        }
    }

    private fun sortLowPriority() {
        viewModelScope.launch {
            useCase.sortByLowPriorityUseCase.invoke().collectLatest {
                _sortByLowPriority.value = it
            }
        }
    }

    fun getAllData() {
        viewModelScope.launch {
            useCase.getAllNoteUseCase.invoke().collect {
                _getAllData.postValue(it)
            }
        }
    }

    fun searchNote(query: String) {
        viewModelScope.launch {
            useCase.searchNoteUseCase.invoke(query).collect {
                _searchNote.postValue(it)
            }
        }
    }

    fun insertData(noteData: NoteDataEntity) {
        viewModelScope.launch {
            useCase.insertNoteUseCase.invoke(noteData)
        }
    }

    fun updateData(noteData: NoteDataEntity) {
        viewModelScope.launch {
            useCase.updateNoteUseCase.invoke(noteData)
        }
    }

    fun deleteData(noteData: NoteDataEntity) {
        viewModelScope.launch {
            useCase.deleteNoteUseCase.invoke(noteData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            useCase.deleteAllNoteUseCase.invoke()
        }
    }

}