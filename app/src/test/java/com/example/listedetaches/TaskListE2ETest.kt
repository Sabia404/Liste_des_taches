package com.example.listedetaches

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.listedetaches.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TaskListE2ETest {

    // Lance MainActivity avant chaque test
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    // Repart avec un repository vide avant chaque test
    @Before
    fun setUp() {
        MainActivity.repository.getAllTasks().forEach {
            MainActivity.repository.deleteTask(it.id)
        }
    }

    // Parcours complet : créer une tâche et vérifier son affichage
    @Test
    fun testCreerTache_apparaitDansLaListe() {
        // Clic sur le bouton flottant +
        onView(withId(R.id.fabAddTask)).perform(click())

        // Remplir le titre
        onView(withId(R.id.etTitle)).perform(typeText("Tâche test"), closeSoftKeyboard())

        // Remplir la description
        onView(withId(R.id.etDescription)).perform(typeText("Description test"), closeSoftKeyboard())

        // Clic sur Enregistrer
        onView(withId(R.id.btnSave)).perform(click())

        // Vérifier que la tâche apparait dans la liste
        onView(withText("Tâche test")).check(matches(isDisplayed()))
    }

    // Parcours : créer une tâche avec titre vide affiche une erreur
    @Test
    fun testCreerTache_sansTitre_afficheErreur() {
        // Clic sur le bouton flottant +
        onView(withId(R.id.fabAddTask)).perform(click())

        // Clic sur Enregistrer sans titre
        onView(withId(R.id.btnSave)).perform(click())

        // Vérifier qu'on est encore sur l'écran formulaire
        onView(withId(R.id.btnSave)).check(matches(isDisplayed()))
    }
}