package com.example.listedetaches.view

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.listedetaches.R
import com.example.listedetaches.contract.TaskFormContract
import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Priority
import com.example.listedetaches.model.Task
import com.example.listedetaches.presenter.TaskFormPresenter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

// Code généré à l'aide de Claude (Anthropic)
// Description : Activité du formulaire d'ajout et modification d'une tâche

class TaskFormActivity : AppCompatActivity(), TaskFormContract.View {

    private lateinit var presenter: TaskFormPresenter
    private lateinit var etTitle: TextInputEditText
    private lateinit var tilTitle: TextInputLayout
    private lateinit var etDescription: TextInputEditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerPriority: Spinner
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)

        // Références aux éléments visuels
        etTitle = findViewById(R.id.etTitle)
        tilTitle = etTitle.parent.parent as TextInputLayout
        etDescription = findViewById(R.id.etDescription)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        spinnerPriority = findViewById(R.id.spinnerPriority)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)

        // Création du présentateur avec le repository partagé
        presenter = TaskFormPresenter(this, MainActivity.repository)

        // Remplit le spinner des catégories
        val categories = Category.values().map { it.name }
        spinnerCategory.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, categories).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        // Remplit le spinner des priorités
        val priorities = Priority.values().map { it.name }
        spinnerPriority.adapter = ArrayAdapter(this,
            android.R.layout.simple_spinner_item, priorities).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

        // Charge la tâche si on est en mode modification
        val taskId = intent.getStringExtra("TASK_ID")
        presenter.loadTask(taskId)

        // Clic sur Enregistrer
        btnSave.setOnClickListener {
            presenter.saveTask(
                title = etTitle.text.toString(),
                description = etDescription.text.toString(),
                category = Category.valueOf(spinnerCategory.selectedItem.toString()),
                priority = Priority.valueOf(spinnerPriority.selectedItem.toString())
            )
        }

        // Clic sur Annuler
        btnCancel.setOnClickListener { presenter.onCancelClicked() }
    }

    // Affiche un message d'erreur sous le champ titre
    override fun showTitleError(message: String) {
        tilTitle.error = message
    }

    // Retourne à l'écran précédent
    override fun navigateBack() {
        finish()
    }

    // Remplit les champs avec les données de la tâche existante
    override fun populateFields(task: Task) {
        etTitle.setText(task.title)
        etDescription.setText(task.description)
        spinnerCategory.setSelection(Category.values().indexOf(task.category))
        spinnerPriority.setSelection(Priority.values().indexOf(task.priority))
    }
}