package com.example.notesapplication.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapplication.R
import com.example.notesapplication.data.model.NoteDataEntity
import com.example.notesapplication.databinding.FragmentUpdateBinding
import com.example.notesapplication.presentation.viewmodels.NoteViewModel
import com.example.notesapplication.presentation.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateFragment : Fragment(R.layout.fragment_update) {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val noteVm: NoteViewModel by viewModels()
    private val sharedVm: SharedViewModel by viewModels()

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentUpdateBinding.bind(view)

        binding.args = args
        binding.btnUpdate.setOnClickListener(View.OnClickListener {
            updateNote()
        })
        binding.btnDelete.setOnClickListener(View.OnClickListener {
            deleteNote()
        })


    }

    private fun updateNote() {
        val title = binding.etUpdateNoteTitle.text.toString()
        val descNote = binding.etUpdateNoteDesc.text.toString()
        //val getPriority = binding.sUpdateNote.selectedItem.toString()

        val validation = sharedVm.verifyDataFromUser(title, descNote)
        if (validation) {
            val updateNoteData = NoteDataEntity(
                args.noteParcel.id,
                title,
                descNote,
                sharedVm.parsePriority("HIGH")
            )
            noteVm.updateData(updateNoteData)
            Toast.makeText(
                requireContext(),
                "Successfully Updated.",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(
                requireContext(),
                "Please Fill Out All Fields.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete '${args.noteParcel.title}?'")
        builder.setMessage("Are you sure you want to remove '${args.noteParcel.title}?'")
        builder.setPositiveButton("Yes") { _, _ ->
            noteVm.deleteData(args.noteParcel)
            Toast.makeText(
                requireContext(),
                "Successfully Removed: ${args.noteParcel.title}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}