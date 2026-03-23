package com.example.listedetaches

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import  android.content.Intent
import repo.TaskRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val button = findViewById<Button>(R.id.btnAjouter)

        button.setOnClickListener {
            val intent = Intent(this,TaskFormActivity::class.java)
            startActivity(intent)
        }
//        val repo = TaskRepository();
//        val task = Task(
//            "1",
//            "Tester",
//            "Test",
//            Category.SCHOOL,
//            Priority.MEDIUM,
//            false,
//            System.currentTimeMillis()
//        )
//
//        repo.addTask(task)
//        println(repo.getTask())
    }




}