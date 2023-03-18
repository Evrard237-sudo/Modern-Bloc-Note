package com.archcomp.notesyttutorial.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.archcomp.notesyttutorial.Database.NoteDatabase
import com.archcomp.notesyttutorial.Database.NotesRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * est une sous-classe de la classe AndroidViewModel,
     * qui fournit une architecture de base pour créer une ViewModel dans une application Android
     */

    private val repository : NotesRepository

    val allnotes : LiveData<List<Note>> // Ceci represente la liste des notes qui ont été crée dans la base de données

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NotesRepository(dao) // On l'affecte a la variable repository a la dao de NotesRepository
        allnotes = repository.allNotes // elle recuperer les notes envoyer par la allNotes de NotesRepository
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.delete(note) // On recupere la fonction de suppression de la dao
    }

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insert(note) // On recupere la fonction d'insertion de la dao
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.update(note) // On recupere la fonction de modification de a dao
    }

}