package com.example.listedetaches.contract

import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Priority
import com.example.listedetaches.model.Task

interface TaskFormContract {

    interface View{

        //  Affiche un message d'erreur sous le champ titre
        //  quand il est vide
        fun showTitleError(message : String)

        //  Retourne à l'écran précédent (après sauvegarde ou annulation)
        fun navigateBack()

        // Remplit les champs avec les données d'une tâche existante
        // quand on veut la modifier
        fun populateFields(task: Task)
    }

    interface Presenter{

        // Reçoit tous les champs du formulaire, valide et sauvegarde
        fun saveTask(
            title: String,
            description: String,
            category: Category,
            priority: Priority
        )


        // Savoir si on est en mode ajout ou modification
        fun loadTask(taskId: String?)

        // L'utilisateur a cliqué Annuler
        fun onCancelClicked()
    }
}