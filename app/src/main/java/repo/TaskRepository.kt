package repo

import com.example.listedetaches.Task

class TaskRepository {
    private val tasks = mutableListOf<Task>();

    // Permet d'ajouter une tache dans la liste tasks
    fun addTask(task : Task){
        tasks.add(task)
    }

    // Permet de supprimer une tache dans la liste tasks
    fun deleteTask(task : Task){
        tasks.remove(task)
    }

    fun updateTask(task : Task){
        val index = tasks.indexOfFirst { it.id == task.id }
        if(index != -1){
            tasks[index] = task
        }
    }

    // Permet de retouner la liste des taches
    fun getTask(): List<Task>{
       return tasks;
    }
}

