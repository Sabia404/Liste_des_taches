package com.example.listedetaches.repository

import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Task

class TaskRepository {
    //Une liste vide qui peut etre modier -> Mutualble
    private val tasks = mutableListOf<Task>();


    // Permet d'ajouter une tache dans la liste tasks
    fun addTask(task : Task){
        tasks.add(task)
    }

    // Permet de supprimer une tache dans la liste tasks
    fun deleteTask(taskId : String){
        tasks.removeAll { it.id == taskId }
    }

    // IndexOfFirst {it.id == task.id} ->> Permet de parcourir la liste et retourne
    // la position de la tache qui a le meme id
    // Verifier si la tache existe
    // Remplace a cette position
    fun updateTask(task : Task){
        val index = tasks.indexOfFirst { it.id == task.id }
        if(index != -1){
            tasks[index] = task
        }
    }

    // Permet de retouner la liste des taches
    fun getAllTasks(): List<Task>{
       return tasks;
    }

    //Permet de filter par Categorie
    fun getTasksByCategory(category : Category): List<Task> = tasks.filter { it.category == category }

    //Permet d'afficher  les taches qui sont completes
    fun getCompletedTasks(): List<Task> = tasks.filter { it.isDone }

    // Permet d'afficher les taches qui ne sont pas completes
    fun getPendingTasks(): List<Task> = tasks.filter { !it.isDone }
}

