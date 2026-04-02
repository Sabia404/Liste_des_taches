package com.example.listedetaches

import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Priority
import com.example.listedetaches.model.Task
import com.example.listedetaches.repository.TaskRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

// Code généré à l'aide de Claude (Anthropic)
// Description : Tests unitaires du repository

class TaskRepositoryTest {

    private lateinit var repository: TaskRepository

    // Executé avant chaque test — repart avec un repository vide
    @Before
    fun setUp() {
        repository = TaskRepository()
    }

    // Vérifie qu'on peut ajouter une tâche et la retrouver dans la liste
    @Test
    fun testAjoutTache_retourneLaTacheDansLaListe() {
        val tache = Task(
            title = "Faire l'épicerie",
            description = "Acheter du lait",
            category = Category.PERSONAL,
            priority = Priority.HIGH,
            isDone = false
        )
        repository.addTask(tache)
        assertTrue(repository.getAllTasks().contains(tache))
    }

    // Vérifie que la suppression retire bien la tâche
    @Test
    fun testSuppression_retireCorrectementLaTache() {
        val tache = Task(
            title = "Tâche à supprimer",
            description = "",
            category = Category.OTHER,
            priority = Priority.LOW,
            isDone = false
        )
        repository.addTask(tache)
        repository.deleteTask(tache.id)
        assertFalse(repository.getAllTasks().contains(tache))
    }

    // Vérifie que le filtrage par catégorie retourne seulement les bonnes tâches
    @Test
    fun testFiltrageParCategorie_retourneSeulementLesBonnesTaches() {
        val tacheEcole = Task(
            title = "Devoir",
            description = "",
            category = Category.SCHOOL,
            priority = Priority.HIGH,
            isDone = false
        )
        val tacheTravail = Task(
            title = "Réunion",
            description = "",
            category = Category.WORK,
            priority = Priority.MEDIUM,
            isDone = false
        )
        repository.addTask(tacheEcole)
        repository.addTask(tacheTravail)
        val resultats = repository.getTasksByCategory(Category.SCHOOL)
        assertTrue(resultats.all { it.category == Category.SCHOOL })
        assertFalse(resultats.contains(tacheTravail))
    }

    // Vérifie que le changement de statut met à jour correctement la tâche
    @Test
    fun testChangementStatut_metAJourCorrectement() {
        val tache = Task(
            title = "Étudier",
            description = "",
            category = Category.SCHOOL,
            priority = Priority.HIGH,
            isDone = false
        )
        repository.addTask(tache)
        val tacheMiseAJour = tache.copy(isDone = true)
        repository.updateTask(tacheMiseAJour)
        val resultat = repository.getAllTasks().find { it.id == tache.id }
        assertTrue(resultat?.isDone == true)
    }

    // Vérifie que getCompletedTasks retourne seulement les tâches complétées
    @Test
    fun testTachesCompletes_retourneSeulementLesCompletes() {
        repository.addTask(Task(
            title = "Faite",
            description = "",
            category = Category.OTHER,
            priority = Priority.LOW,
            isDone = true
        ))
        repository.addTask(Task(
            title = "Pas faite",
            description = "",
            category = Category.OTHER,
            priority = Priority.LOW,
            isDone = false
        ))
        val completes = repository.getCompletedTasks()
        assertTrue(completes.all { it.isDone })
    }

    // Vérifie que getPendingTasks retourne seulement les tâches non complétées
    @Test
    fun testTachesAFaire_retourneSeulementLesNonCompletes() {
        repository.addTask(Task(
            title = "Faite",
            description = "",
            category = Category.OTHER,
            priority = Priority.LOW,
            isDone = true
        ))
        repository.addTask(Task(
            title = "À faire",
            description = "",
            category = Category.OTHER,
            priority = Priority.LOW,
            isDone = false
        ))
        val aFaire = repository.getPendingTasks()
        assertTrue(aFaire.all { !it.isDone })
    }
}