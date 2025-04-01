package com.example.mealapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class Suggestionone : AppCompatActivity() {
    private lateinit var resultsTextView: TextView
    private lateinit var resetButton: Button
    private lateinit var exitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_suggestionone)


        resetButton = findViewById(R.id.resetBtn)
        exitButton = findViewById(R.id.exitBtn)
        resultsTextView = findViewById(R.id.resultsText)


     // used intent instead of clear() because it provided the reset function of application
        resetButton.setOnClickListener {

            Toast.makeText(this,"cleared and returned to the main page",Toast.LENGTH_SHORT).show()
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        exitButton.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }


        val mealSuggestion: String? = intent.getStringExtra("TimeOfDay")

        resultsTextView.text = mealSuggestion ?: "No meal suggestion available"



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

    }


    }

