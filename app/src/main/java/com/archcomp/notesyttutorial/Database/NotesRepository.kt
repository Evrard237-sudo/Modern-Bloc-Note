package com.archcomp.notesyttutorial.Database

import androidx.lifecycle.LiveData
import com.archcomp.notesyttutorial.Models.Note

class NotesRepository(private val noteDao: NoteDao) {

    val allNotes : LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        // On apelle le controlleur d'insertion dans noteDao
        noteDao.insert(note)

    }

    suspend fun delete(note: Note){
        // On apelle le controlleur de suppression dans noteDao
        noteDao.delete(note)

    }

    suspend fun update(note: Note){
        // On apelle le controlleur de modification dans noteDao
        noteDao.update(note.id, note.title, note.note)

    }

}