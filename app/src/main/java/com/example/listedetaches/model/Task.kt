package com.example.listedetaches.model

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