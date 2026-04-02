package com.example.listedetaches.presenter

import com.example.listedetaches.contract.TaskFormContract
import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Priority
import com.example.listedetaches.model.Task
import com.example.listedetaches.repository.TaskRepository

class TaskFormPresenter(
    private val view: TaskFormContract.View,
    private val repository: TaskRepository
) : TaskFormContract.Presenter {

    //  Stocke la tâche qu'on est en train de modifier.
    //  Si c'est un ajout, elle reste null
    private var existingTask: Task? = null

    // Si taskId est null on fait rien (return),
    // sinon on cherche la tâche et on remplit les champs
    override fun loadTask(taskId: String?) {
        if (taskId == null) return
        val task = repository.getAllTasks().find { it.id == taskId }
        if (task != null) {
            existingTask = task
            view.populateFields(task)
        }
    }

    override fun saveTask(
        title: String,
        description: String,
        category: Category,
        priority: Priority
    ) {
        // Vérifie que le titre est pas vide ou juste des espaces
        if (title.isBlank()) {
            view.showTitleError("Le titre ne peut pas être vide.")
            return
        }

        // Si on modifie une tâche existante,
        // On fait une copie avec les nouvelles valeurs
        val task = existingTask?.copy(
            title = title,
            description = description,
            category = category,
            priority = priority
        ) ?: Task(
            title = title,
            description = description,
            category = category,
            priority = priority,
            isDone = false

        )

        if (existingTask != null) repository.updateTask(task)
        else repository.addTask(task)

        view.navigateBack()
    }

    override fun onCancelClicked() {
        view.navigateBack()
    }
}