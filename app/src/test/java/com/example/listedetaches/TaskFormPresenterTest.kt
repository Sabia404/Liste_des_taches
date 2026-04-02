package com.example.listedetaches

import com.example.listedetaches.contract.TaskFormContract
import com.example.listedetaches.model.Category
import com.example.listedetaches.model.Priority
import com.example.listedetaches.model.Task
import com.example.listedetaches.presenter.TaskFormPresenter
import com.example.listedetaches.repository.TaskRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

// Code généré à l'aide de Claude (Anthropic)
// Description : Tests unitaires du présentateur du formulaire

class TaskFormPresenterTest {

    private lateinit var repository: TaskRepository
    private lateinit var presenter: TaskFormPresenter
    private var titleError: String? = null
    private var navigatedBack = false

    // Fausse vue pour simuler l'interface sans Android
    private val fakeView = object : TaskFormContract.View {
        override fun showTitleError(message: String) { titleError = message }
        override fun navigateBack() { navigatedBack = true }
        override fun populateFields(task: Task) {}
    }

    // Repart avec un état propre avant chaque test
    @Before
    fun setUp() {
        repository = TaskRepository()
        presenter = TaskFormPresenter(fakeView, repository)
        titleError = null
        navigatedBack = false
    }

    // Vérifie qu'un titre vide affiche une erreur
    @Test
    fun testSauvegarde_avecTitreVide_afficheErreur() {
        presenter.saveTask("", "desc", Category.WORK, Priority.HIGH)
        assertNotNull(titleError)
        assertFalse(navigatedBack)
    }

    // Vérifie qu'un titre valide sauvegarde et navigue en arrière
    @Test
    fun testSauvegarde_avecTitreValide_navigueVersListe() {
        presenter.saveTask("Titre valide", "desc", Category.SCHOOL, Priority.LOW)
        assertNull(titleError)
        assertTrue(navigatedBack)
    }

    // Vérifie que la tâche est bien ajoutée dans le repository
    @Test
    fun testSauvegarde_ajouteTacheDansRepository() {
        presenter.saveTask("Ma tâche", "description", Category.PERSONAL, Priority.MEDIUM)
        assertEquals(1, repository.getAllTasks().size)
        assertEquals("Ma tâche", repository.getAllTasks().first().title)
    }

    // Vérifie que Annuler retourne à l'écran précédent
    @Test
    fun testAnnuler_navigueVersListe() {
        presenter.onCancelClicked()
        assertTrue(navigatedBack)
    }
}