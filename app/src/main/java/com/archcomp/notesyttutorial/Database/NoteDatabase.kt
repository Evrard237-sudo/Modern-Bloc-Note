package com.archcomp.notesyttutorial.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.archcomp.notesyttutorial.Models.Note
import com.archcomp.notesyttutorial.utilities.DATABASE_NAME


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false/*La Bd ne sera pas exporté sur fichier GSON*/)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDao // Cette méthode abstraite est nécessaire pour que Room puisse générer le code d'implémentation de l'interface NoteDao au moment de la compilation.

    companion object{

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context) : NoteDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance

                instance
            }

        }

    }

}