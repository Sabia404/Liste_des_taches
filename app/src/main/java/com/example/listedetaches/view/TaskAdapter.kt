package com.example.listedetaches.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listedetaches.R
import com.example.listedetaches.model.Priority
import com.example.listedetaches.model.Task

class TaskAdapter(
    private var tasks: List<Task>,
    private val onTaskClick: (Task) -> Unit,        // Clic sur une tâche
    private val onCheckChanged: (Task) -> Unit,     // Clic sur la checkbox
    private val onTaskLongClick: (Task) -> Unit     // Appui long sur une tâche
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    // Contient les références aux éléments visuels d'une ligne
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val checkBoxDone: CheckBox = itemView.findViewById(R.id.checkBoxDone)
        val viewPriority: View = itemView.findViewById(R.id.viewPriorityIndicator)
    }

    // Crée une nouvelle ligne en gonflant le layout item_task.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    // Remplit une ligne avec les données d'une tâche
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        // Affiche le titre et la catégorie
        holder.tvTitle.text = task.title
        holder.tvCategory.text = task.category.name

        // Couleur de la barre de priorité
        holder.viewPriority.setBackgroundColor(
            when (task.priority) {
                Priority.HIGH -> Color.RED
                Priority.MEDIUM -> Color.parseColor("#FFA500") // Orange
                Priority.LOW -> Color.GREEN
            }
        )

        // Remet le listener à null pour éviter les déclenchements involontaires
        holder.checkBoxDone.setOnCheckedChangeListener(null)
        holder.checkBoxDone.isChecked = task.isDone
        holder.checkBoxDone.setOnCheckedChangeListener { _, _ -> onCheckChanged(task) }

        // Clic simple pour modifier la tâche
        holder.itemView.setOnClickListener { onTaskClick(task) }

        // Appui long pour supprimer la tâche
        holder.itemView.setOnLongClickListener {
            onTaskLongClick(task)
            true
        }
    }

    // Retourne le nombre de tâches dans la liste
    override fun getItemCount() = tasks.size

    // Met à jour la liste et rafraîchit l'affichage
    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}