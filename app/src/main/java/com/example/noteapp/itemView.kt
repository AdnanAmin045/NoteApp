package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.view.menu.MenuView.ItemView
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.databinding.ActivityItemViewBinding

class itemView : AppCompatActivity() {
    private lateinit var binding:ActivityItemViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityItemViewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.delete.setOnClickListener {

        }
    }

    }