package com.example.noteapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,DBName,null, DB_version) {
    companion object{
        private const val DBName = "Note_DataDB"
        private const val  DB_version = 1
        private const val TABLE_name = "Data"
        private const val ID = "id"
        private const val title = "title"
        private val content = "content"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        var query = "CREATE TABLE $TABLE_name($ID INTEGER PRIMARY KEY,$title TEXT,$content TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       var query = "DROP TABLE IF EXISTS $TABLE_name"
        db?.execSQL(query)
        onCreate(db)
    }
    fun insert(data:Data){
        var db = writableDatabase
        var ins = ContentValues().apply {
            put(title,data.title)
            put(content,data.content)
        }
        db.insert(TABLE_name,null,ins)
        db.close()

    }
    fun getData(): List<Data>{
        var listData = mutableListOf<Data>()
        var db = readableDatabase
        var query = "SELECT * FROM $TABLE_name"
        var cursor = db.rawQuery(query,null)
        while (cursor.moveToNext()){
            var id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
            var title = cursor.getString(cursor.getColumnIndexOrThrow(title))
            var content = cursor.getString(cursor.getColumnIndexOrThrow(content))
            var data = Data(id,title, content)
            listData.add(data)
        }
        cursor.close()
        db.close()
        return listData
    }
    fun updateData(note:Data)
    {
        var db = writableDatabase
        var values = ContentValues().apply{
            put(title,note.title)
            put(content,note.content)
        }
        var whereClause = "$ID = ?"
        var whereArgs = arrayOf(note.id.toString())
        db.update(TABLE_name,values,whereClause,whereArgs)

         db.close()
    }
    fun getNoteId(noteId: Int) : Data{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_name WHERE $ID = $noteId"
        val cursor = db.rawQuery(query,null)
        cursor.moveToFirst()
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(title))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(content))

        cursor.close()
        db.close()
        return Data(id,title,content)
    }
    fun delete(noteId: Int){
        val db = writableDatabase
        val whereClause = "$ID = ?"
        val whereArgs = arrayOf(noteId.toString())
        db.delete(TABLE_name,whereClause,whereArgs)
        db.close()
    }




}