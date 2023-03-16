package com.example.notesapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapplication.common.NoteDiffUtil
import com.example.notesapplication.data.model.NoteDataEntity
import com.example.notesapplication.databinding.ItemNoteLayoutBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var noteData = listOf<NoteDataEntity>()

    class MyViewHolder(private val binding: ItemNoteLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(noteData: NoteDataEntity) {
            binding.noteData = noteData
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(noteData[position])
        for (s in 0..noteData.size){

        }
    }

    override fun getItemCount(): Int = noteData.size

    fun setData(newData: List<NoteDataEntity>) {
        val noteDiffUtil = NoteDiffUtil(noteData, newData)
        val diffUtilResult = DiffUtil.calculateDiff(noteDiffUtil)
        noteData = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}