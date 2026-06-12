package com.example.slebew_apps.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.slebew_apps.data.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteEntity>

    @Insert
    suspend fun insert(note: NoteEntity)

    @androidx.room.Delete
    suspend fun delete(note: NoteEntity)

    @androidx.room.Update
    suspend fun update(note: NoteEntity)
}
