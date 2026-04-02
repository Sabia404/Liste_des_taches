package com.example.listedetaches.presenter

import com.example.listedetaches.contract.TaskListContract
import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Task
import com.example.listedetaches.repository.TaskRepository

// Code généré à l'aide de Claude (Anthropic)
// Description : Présentateur gérant la logique de la liste des tâches


// Le presentateur recoit deux params à da création
// 1. View : Pour parler à la Vue
// 2. Repository : Pour accéder aux données
// TaskListContract.Presenter : Obligé d'implémenter tous les fonctions
class TaskListPresenter(
    private val view: TaskListContract.View,
    private val repository: TaskRepository
) : TaskListContract.Presenter {

    override fun loadTasks() {
        val tasks = repository.getAllTasks()
        if (tasks.isEmpty()) view.showEmptyState()
        else view.showTasks(tasks)
    }

    override fun filterByCategory(category: Category) {
        val tasks = repository.getTasksByCategory(category)
        if (tasks.isEmpty()) view.showEmptyState()
        else view.showTasks(tasks)
    }

    override fun filterByStatus(showCompleted: Boolean) {
        val tasks = if (showCompleted) repository.getCompletedTasks()
        else repository.getPendingTasks()
        if (tasks.isEmpty()) view.showEmptyState()
        else view.showTasks(tasks)
    }


    /*
    * Si isDone était true -> Il devient false
    * Si isDone était false -> Il devient true
    *
    * On utilise plutot copy() puisque Task est un data class. De plus,
    * dans kotlin onne modifie pas directement les propriétées. Donc, on fait
    * une copy
    *
    * */
    override fun toggleTaskDone(task: Task) {
        val updated = task.copy(isDone = !task.isDone)
        repository.updateTask(updated)
        loadTasks()
    }

    override fun deleteTask(task: Task) {
        repository.deleteTask(task.id)
        loadTasks()
    }

    override fun onAddTaskClicked() {
        view.navigateToAddTask()
    }

    override fun onTaskClicked(task: Task) {
        view.navigateToEditTask(task)
    }

    override fun clearFilter() {
        loadTasks()
    }
}