package com.example.notesapplication.presentation.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesapplication.R
import com.example.notesapplication.common.observeOnce
import com.example.notesapplication.databinding.ActivityMainBinding
import com.example.notesapplication.databinding.FragmentListBinding
import com.example.notesapplication.presentation.adapters.ListAdapter
import com.example.notesapplication.presentation.viewmodels.NoteViewModel
import com.example.notesapplication.presentation.viewmodels.SharedViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.fragment_list) {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val listAdapter: ListAdapter by lazy { ListAdapter() }
    private val sharedViewModel: SharedViewModel by viewModels()
    private val noteViewModel: NoteViewModel by viewModels()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.sharedVm = sharedViewModel

        setupRecycler()
        setupViewModel()



    }



    private fun setupRecycler() {
        val recyclerView = binding.rvListFragment
        recyclerView.adapter = listAdapter
        recyclerView.layoutManager =LinearLayoutManager(requireContext())

        recyclerView.setHasFixedSize(true)

    }



    private fun setupViewModel() {
        noteViewModel.getAllData()
        noteViewModel.getAllData.observe(viewLifecycleOwner) { data ->
            sharedViewModel.checkIfDatabaseEmpty(data)
            listAdapter.setData(data)
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}