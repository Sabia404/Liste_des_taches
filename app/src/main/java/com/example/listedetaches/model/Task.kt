package com.example.listedetaches.model

// Code généré à l'aide de Claude (Anthropic)
// Description : Modèle de données représentant une tâche

import java.util.UUID
data class Task(

    // UUID permet de generer un identifiant unique automatique
    val id:String = UUID.randomUUID().toString(),
    var title : String,
    var description : String,
    var category : Category,
    var priority: Priority,
    var isDone : Boolean,
    // currrentTimeMillis permet de retourner la date et l'heure
    val dateCreation : Long = System.currentTimeMillis()



)