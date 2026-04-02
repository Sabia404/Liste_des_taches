package com.example.listedetaches.presenter

import com.example.listedetaches.contract.TaskListContract
import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Task
import com.example.listedetaches.repository.TaskRepository

// Code généré à l'aide de Claude (Anthropic)
// Description : Présentateur gérant la logique de la liste des tâches

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