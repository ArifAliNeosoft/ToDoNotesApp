package com.example.notesapplication.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.notesapplication.R
import com.example.notesapplication.data.model.NoteDataEntity
import com.example.notesapplication.data.model.NotePriority
import com.example.notesapplication.databinding.FragmentAddBinding
import com.example.notesapplication.presentation.viewmodels.NoteViewModel
import com.example.notesapplication.presentation.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val noteVm: NoteViewModel by viewModels()
    private val sharedVm: SharedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)
        binding.btnSave.setOnClickListener(View.OnClickListener { insertDataToDatabase() })
    }


    private fun insertDataToDatabase() {
        val noteTitle = binding.etAddNoteTitle.text.toString()
        val noteDesc = binding.etAddNoteMultiLine.text.toString()
        val validation = sharedVm.verifyDataFromUser(noteTitle, noteDesc)
        var colorPriority=""
        val priority=(0..3).random()
        Log.e("TAG=","===="+priority.toString())
        if (priority==0){
            colorPriority=NotePriority.HIGH.toString()
            Log.e("TAG=","===="+"Inside High")
        }else if (priority==1){
           colorPriority=NotePriority.MEDIUM.toString()
            Log.e("TAG=","===="+"Inside Medium")
        }else {
           colorPriority=NotePriority.LOW.toString()
            Log.e("TAG=","===="+"Inside Low")
        }

        if (validation) {
            val newData = NoteDataEntity(
                0,
                noteTitle,
                noteDesc,
                sharedVm.parsePriority(colorPriority)
            )
            noteVm.insertData(newData)
            Toast.makeText(
                requireContext(),
                "Successfully added.",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "Please fill out all fields.",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}