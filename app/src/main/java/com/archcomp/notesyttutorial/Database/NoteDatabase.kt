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

        /**
         * elle définit un objet compagnon (companion object) pour la classe NoteDatabase qui fournit
         * une méthode de création d'instances de la base de données
         * */

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context) : NoteDatabase{

            /**
             * Cette fonction est une méthode statique qui permet d'obtenir une instance de la classe NoteDatabase.
             * Elle prend en entrée un objet de type Context, qui est utilisé pour accéder aux ressources
             * et aux services du système Android, et elle renvoie une instance de la classe NoteDatabase.
             * La méthode utilise la méthode synchronized() pour garantir que la création
             * de la base de données se fait de manière thread-safe. Elle crée ensuite une
             * instance de Room.databaseBuilder() en utilisant le nom de la classe NoteDatabase,
             * le nom de la base de données et la version de la base de données.
             * */

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()

                // Bref elle recupere l'instance de Note pour crée la base de données
                INSTANCE = instance

                instance
            }

        }

    }

}