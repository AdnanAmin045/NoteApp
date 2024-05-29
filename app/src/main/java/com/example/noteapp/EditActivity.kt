package com.example.noteapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteapp.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var db: DBHelper
    private var noteId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


                db = DBHelper(this)
                noteId = intent.getIntExtra("noteId", -1)
                if (noteId == -1) {
                    finish()
                }

                val note = db.getNoteId(noteId)
                binding.editTitleTxt.setText(note.title)
                binding.editContentTxt.setText(note.content)

                binding.editSave.setOnClickListener {
                    if(binding.editTitleTxt.text.toString().isEmpty() || binding.editContentTxt.text.toString().isEmpty())
                    {
                            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        val newTitle = binding.editTitleTxt.text.toString()
                        val newContent = binding.editContentTxt.text.toString()
                        val newData = Data(noteId, newTitle, newContent)

                        db.updateData(newData)
                        finish()
                        Toast.makeText(this, "Changes Saved", Toast.LENGTH_SHORT).show()
                    }


                }
        }

    }