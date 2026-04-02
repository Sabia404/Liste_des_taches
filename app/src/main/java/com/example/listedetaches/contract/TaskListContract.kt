package com.example.listedetaches.contract

import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Task

// Code généré à l'aide de Claude (Anthropic)
// Description : Contrat MVP définissant les interactions entre la Vue et le Présentateur de la liste
interface TaskListContract {


 // Le Presentateur appelle ces fonctions
    interface View{
        // Afficher taches
        fun showTasks(tasks: List<Task>)

        // Afficher un message lorsque la liste est vide
        fun showEmptyState()

        // Naviguer vers l'ecran d'ajout des taches
        fun navigateToAddTask()

        // Naviguer vers l'ecran modifier des taches
        fun navigateToEditTask(task: Task)
    }

    // La Vue demande au Presentateur
    interface Presenter{

        // Charger toutes les tâches depuis le Repository
        // et de les envoyer à la Vue.
        fun loadTasks()

        // La Vue dit au Présentateur "filtre par cette catégorie"
        fun filterByCategory(category: Category)

        // La Vue dit au Présentateur de filtrer par statut
        fun filterByStatus(showCompleted: Boolean)

        //l'utilisateur coche/décoche une tâche,
        // la Vue appelle cette fonction
        fun toggleTaskDone(task: Task)

        // La Vue dit au Présentateur "supprime cette tâche".
        fun deleteTask(task: Task)

        // l'utilisateur clique sur le bouton +,
        // la Vue informe le Présentateur.
        fun onAddTaskClicked()

        // Quand l'utilisateur clique sur une tâche,
        // la Vue informe le Présentateur
        fun onTaskClicked(task: Task)

        // Quand l'utilisateur enlève le filtre,
        // la Vue appelle cette fonction
        fun clearFilter()
    }
}