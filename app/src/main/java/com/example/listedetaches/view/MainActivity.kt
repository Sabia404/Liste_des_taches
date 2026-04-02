package com.example.listedetaches.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listedetaches.R
import com.example.listedetaches.contract.TaskListContract
import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Task
import com.example.listedetaches.presenter.TaskListPresenter
import com.example.listedetaches.repository.TaskRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

// Code généré à l'aide de Claude (Anthropic)
// Description : Activité principale affichant la liste des tâches

// TaskListContract.View doit implementé toutes ses funs
class MainActivity : AppCompatActivity(), TaskListContract.View {

    // Les lateinit var seront utilisé dans onCreate()
    private lateinit var presenter: TaskListPresenter
    private lateinit var adapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmpty: TextView
    private lateinit var spinnerFilter: Spinner
    private lateinit var fab: FloatingActionButton

    // Repository partagé en mémoire entre les deux activités (MainActivity et TaskFormActivity)
    companion object {
        val repository = TaskRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Références aux éléments visuels
        recyclerView = findViewById(R.id.recyclerViewTasks)
        tvEmpty = findViewById(R.id.tvEmpty)
        spinnerFilter = findViewById(R.id.spinnerFilter)
        fab = findViewById(R.id.fabAddTask)

        // Création du présentateur
        presenter = TaskListPresenter(this, repository)

        // Création de l'adaptateur avec les événements
        adapter = TaskAdapter(
            tasks = emptyList(),
            onTaskClick = { task -> presenter.onTaskClicked(task) },
            onCheckChanged = { task -> presenter.toggleTaskDone(task) },
            onTaskLongClick = { task -> confirmerSuppression(task) }
        )

        // Configuration du RecyclerView
        //RecyclerView affiche les éléments en liste verticale
        // l'un en dessous de l'autre
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Adaptateur qui va remplir chaque ligne
        recyclerView.adapter = adapter

        // Configuration du menu de filtrage
        configurerFiltres()

        // Clic sur le bouton flottant
        fab.setOnClickListener { presenter.onAddTaskClicked() }
    }

    // Recharge les tâches à chaque retour sur l'écran
    // Par exemple après avoir ajouté ou modifié une tâche.
    override fun onResume() {
        super.onResume()
        presenter.loadTasks()
    }

    // Configure le spinner de filtrage
    private fun configurerFiltres() {
        val options = mutableListOf("Toutes les tâches", "À faire", "Complétées") +
                Category.values().map { it.name }

        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFilter.adapter = adapterSpinner

        spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                when (pos) {
                    0 -> presenter.clearFilter()
                    1 -> presenter.filterByStatus(false)
                    2 -> presenter.filterByStatus(true)
                    else -> presenter.filterByCategory(Category.values()[pos - 3])
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    // Affiche une boîte de dialogue de confirmation avant de supprimer
    private fun confirmerSuppression(task: Task) {
        AlertDialog.Builder(this)
            .setTitle("Supprimer la tâche")
            .setMessage("Voulez-vous supprimer « ${task.title} » ?")
            .setPositiveButton("Supprimer") { _, _ -> presenter.deleteTask(task) }
            .setNegativeButton("Annuler", null)
            .show()
    }

    // Affiche la liste des tâches
    override fun showTasks(tasks: List<Task>) {
        tvEmpty.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        adapter.updateTasks(tasks)
    }

    // Affiche le message vide
    override fun showEmptyState() {
        tvEmpty.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    // Navigue vers l'écran d'ajout
    override fun navigateToAddTask() {
        val intent = Intent(this, TaskFormActivity::class.java)
        startActivity(intent)
    }

    // Navigue vers l'écran de modification avec l'id de la tâche
    override fun navigateToEditTask(task: Task) {
        val intent = Intent(this, TaskFormActivity::class.java)
        intent.putExtra("TASK_ID", task.id)
        startActivity(intent)
    }
}