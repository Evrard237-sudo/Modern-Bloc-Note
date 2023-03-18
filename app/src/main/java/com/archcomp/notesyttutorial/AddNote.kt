package com.archcomp.notesyttutorial

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import com.archcomp.notesyttutorial.Models.Note
import com.archcomp.notesyttutorial.databinding.ActivityAddNoteBinding
import com.archcomp.notesyttutorial.databinding.ActivityMainBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.logging.SimpleFormatter


class AddNote : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private lateinit var note: Note // On prend la classe note
    private lateinit var old_note: Note // Idem
    var isUpdate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        * Ces lignes de code permettent d'inflater la mise en page XML de l'activité
        *  AddNote en utilisant l'utilitaire LayoutInflater et de définir la vue racine de
        * cette activité en utilisant la méthode setContentView().
        */
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            /*
            * Dans ce code, la variable old_note est initialisée en récupérant
            * l'objet Note envoyé depuis l'activité précédente en utilisant l
            * a méthode getSerializableExtra() de l'objet Intent.
            * L'objet Intent est une classe qui est utilisée pour lancer
            * une nouvelle activité ou pour communiquer entre différentes activités.*/
            old_note = intent.getSerializableExtra("current_data") as Note
            binding.etTitle.setText(old_note.title)
            binding.etNote.setText(old_note.note)
            isUpdate = true
        }catch (e : Exception){
            e.printStackTrace() // Sinon s'affiche une exeption
        }

        binding.imgCheck.setOnClickListener {

            val title = binding.etTitle.text.toString()
            val note_desc = binding.etNote.text.toString()

            if(title.isNotEmpty() || note_desc.isNotEmpty()){
                val formatter = DateFormat.getDateInstance().format(Date())

                note = if (isUpdate){
                    Note(
                        old_note.id, title, note_desc, formatter.format(Date())
                    )
                } else{
                    Note(
                        null, title, note_desc, formatter.format(Date())
                    )
                }

                /*
                * Ce code permet de renvoyer les données de la note nouvellement créée à l'activité appelante.
                * La première ligne crée une nouvelle instance de la classe Intent, qui est utilisée pour envoyer des données entre différentes activités de l'application.
                * La deuxième ligne ajoute la note nouvellement créée à l'intention de l'activité appelante en utilisant la méthode putExtra(). Ici, la clé est "note" et la valeur est l'objet Note créé.
                * La troisième ligne utilise la méthode setResult() pour définir le résultat de l'activité en tant que RESULT_OK et renvoie l'intention de l'activité appelante.
                * Enfin, la quatrième ligne ferme l'activité actuelle avec la méthode finish().*/
                val intent = Intent()
                intent.putExtra("note", note)
                setResult(Activity.RESULT_OK,intent)
                finish()
            } else {
                /*
                * Cette partie de code permet d'afficher un message Toast avec le texte "Please enter some data" si l'utilisateur clique sur le bouton de sauvegarde sans remplir les champs du titre et de la note.
                * La méthode makeText() crée un nouveau message Toast avec trois paramètres: le contexte de l'application (dans ce cas, l'activité AddNote), le texte à afficher dans le message et la durée d'affichage du message.
                * La méthode show() affiche ensuite le message Toast à l'utilisateur.
                * La syntaxe return@setOnClickListener est une étiquette de retour qui permet de sortir de la méthode setOnClickListener sans exécuter le reste du code si les champs sont vides.*/
                Toast.makeText(this@AddNote, "Please enter some data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }
    }
}