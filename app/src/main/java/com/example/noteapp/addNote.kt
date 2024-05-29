package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.databinding.ActivityMainBinding

class addNote : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var db = DBHelper(this)
        binding.imageView2.setOnClickListener{
            if(binding.addTitle.text.toString().isEmpty() || binding.addContent.text.toString().isEmpty())
            {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }
            else
            {
                var title = binding.addTitle.text.toString()
                var content = binding.addContent.text.toString()
                var data = Data(0,title,content)
                db.insert(data)
                finish()
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
            }

        }
    }
}