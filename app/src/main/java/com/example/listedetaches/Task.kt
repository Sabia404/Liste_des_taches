package com.example.listedetaches

data class Task(
    val id:String,
    var title : String,
    var description : String,
    var category : Category,
    var priority: Priority,
    var isDone : Boolean,
    val dateCreation : Long



)