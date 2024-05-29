package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var db:DBHelper
    private lateinit var noteAdapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DBHelper(this)
        noteAdapter = Adapter(db.getData(),this)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = noteAdapter
        binding.addBtn.setOnClickListener{
            var intent = Intent(this,addNote::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        noteAdapter.refreshData(db.getData())
    }
}