package com.archcomp.notesyttutorial.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.archcomp.notesyttutorial.Models.Note
import com.archcomp.notesyttutorial.R
import org.w3c.dom.Text
import kotlin.random.Random

class NotesAdapter(private val context: Context, val listener: NotesClickListener) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    /**
     * est une classe qui hérite de la classe RecyclerView.Adapter<NotesAdapter.NoteViewHolder>.
     * Cette classe est utilisée pour afficher une liste de notes dans une RecyclerView dans
     * l'interface utilisateur.
     * Le constructeur de la classe prend deux arguments :
     * context: le contexte dans lequel l'adaptateur est utilisé. Cela peut être utilisé pour accéder aux ressources de l'application.
     * listener: un NotesClickListener qui est utilisé pour détecter les clics sur les éléments de la liste.
     * contient une classe interne NoteViewHolder qui hérite de la classe RecyclerView.ViewHolder
     * */

    private val NotesList = ArrayList<Note>() // On crée une liste d'objet de Note
    private val fullList = ArrayList<Note>() // Idem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        /**
         * La méthode onCreateViewHolder de la classe NotesAdapter est appelée lorsque
         * la vue RecyclerView doit créer une nouvelle vue pour afficher les éléments
         * de la liste. Cette méthode crée et retourne une instance de la classe interne
         * NoteViewHolder qui contient les éléments de la vue.*/
        return NoteViewHolder(
            /*Cette ligne de code crée une vue en utilisant le fichier de layout R.layout.list_item
            en tant que modèle, en utilisant le LayoutInflater associé au contexte spécifié.*/
            LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return NotesList.size // Retourne la taille de la Note
    }



    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        /*
        * Cette méthode est appelée par la classe RecyclerView pour lier les données
        * de la liste des notes (représentées par l'objet Note) avec les vues correspondantes
        * (représentées par l'objet NoteViewHolder).
        * */
        val currentNote = NotesList[position] // Definit l'index de la note
        holder.title.text = currentNote.title // On pend le texte retourner par l'input de titre
        holder.title.isSelected = true // On verifie si elle est selectionnée

        holder.note.text = currentNote.note // On prend le texte retourné par l'input de la note
        holder.date.text = currentNote.date // On prend la date retourner par le label Date
        holder.date.isSelected = true // On verifie si la date est selectionnée

        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(), null))
        /**
         * Dans cette ligne de code, on définit la couleur de fond d'une carte (CardView) qui affiche
         * une note dans la vue RecyclerView. Cette couleur est choisie aléatoirement
         * à partir d'un ensemble de couleurs prédéfinies.
         * La méthode setCardBackgroundColor() est appelée sur l'objet notes_layout qui représente
         * le conteneur de la carte dans la vue. Cette méthode prend en entrée un paramètre qui
         * est la couleur à définir sous forme d'entier.
         * La couleur est déterminée en appelant la méthode randomColor()
         * qui renvoie un entier correspondant à la position d'une couleur dans un tableau de couleurs.
         * */

        holder.notes_layout.setOnClickListener{

            /**
             * Dans cette ligne de code, on définit le comportement à exécuter lorsqu'un utilisateur
             * clique sur la vue qui affiche une note dans la vue RecyclerView.
             * La méthode setOnClickListener() est appelée sur l'objet notes_layout qui représente
             * la vue qui contient la note dans la vue RecyclerView.
             * */

            listener.onitemClicked(NotesList[holder.adapterPosition])

        }

        holder.notes_layout.setOnLongClickListener {
            //Cette ligne de code permet de définir un comportement spécifique lorsque l'utilisateur
            // effectue un clic long sur une vue dans la liste des notes.
            listener.onlongitemClicked(NotesList[holder.adapterPosition], holder.notes_layout)
            true
        }

    }

    fun updateList(newList: List<Note>){

        fullList.clear()
        fullList.addAll(newList)

        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()

    }

    fun filterList(search: String){

        NotesList.clear()

        for(item in fullList){

            if(item.title?.lowercase()?.contains(search.lowercase()) == true ||
                    item.note?.lowercase()?.contains(search.lowercase()) == true){
                NotesList.add(item)
            }


        }

        notifyDataSetChanged()

    }

    fun randomColor() : Int{ // La fonction qui fera un affichage random des notes

        val list = ArrayList<Int>() // On crée une liste d'element qui contiendra nos notes
        /* On ajoute les elements de couleurs dans cette liste */
        list.add(R.color.NoteColor1)
        list.add(R.color.NoteColor2)
        list.add(R.color.NoteColor3)
        list.add(R.color.NoteColor4)
        list.add(R.color.NoteColor5)
        list.add(R.color.NoteColor6)

        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]

    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val notes_layout = itemView.findViewById<CardView>(R.id.card_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val note = itemView.findViewById<TextView>(R.id.tv_note)
        val date = itemView.findViewById<TextView>(R.id.tv_date)


    }

    interface NotesClickListener{
        // Ces methodes vont se fair
        fun onitemClicked(note: Note)
        fun onlongitemClicked(note: Note,cardView: CardView)
    }

}