package com.archcomp.notesyttutorial.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// ceci est data classe "Note" (c'est la table note)
@Entity(tableName = "notes_table") // La on mentionne que la data classe a pour nom de table "notes_table"
data class Note ( // Ceci sont les attributs ou propriétés de la table ou classe "Note"
    @PrimaryKey(autoGenerate = true) val id: Int?, // On spécifie ici que id est la clé primaire et peut s'auto-incrémenté
    @ColumnInfo(name = "title")/* Ici  on specifie le nom de la colonne de la table auquel appartient l'attribut */ var title: String?,
    @ColumnInfo(name = "note")/* Idem */ val note: String?,
    @ColumnInfo(name = "date") /* Idem */ val date: String?


) : java.io.Serializable // La data class note peut être convertie en une séquence d'octets qui peut être
                        // enregistrée dans un fichier ou transmise sur un réseau