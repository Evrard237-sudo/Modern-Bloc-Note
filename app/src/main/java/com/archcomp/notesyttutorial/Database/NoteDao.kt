package com.archcomp.notesyttutorial.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.archcomp.notesyttutorial.Models.Note

// Ceci est la partie controller du projet
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE/*indique que si une entrée avec la même clé primaire existe déjà dans la base de données, elle sera remplacée par la nouvelle entrée*/)
    suspend fun insert(note :Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    fun getAllNotes() : LiveData<List<Note>> // Grace au LiveData on peut voir les données en temps reel dans l'application

    @Query("UPDATE notes_table SET title = :title, note = :note WHERE id = :id")
    suspend fun update(id: Int?, title: String?, note: String?)

}