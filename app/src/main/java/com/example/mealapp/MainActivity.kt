package com.example.mealapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var inputEditText: EditText
    private lateinit var generateButton: Button
    private lateinit var textView3: TextView
    private lateinit var closeBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inputEditText = findViewById(R.id.inputEditText)
        generateButton = findViewById(R.id.generateBtn)
        textView3 = findViewById(R.id.textView3)
       closeBtn = findViewById(R.id.closeBtn)


        generateButton.setOnClickListener {
            val timeOfDay = inputEditText.text.toString().trim()

            if (timeOfDay.isEmpty()) {
                Toast.makeText(this, "Enter Input Please!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener//AI fixed if statement for correction to make sure that when the user doesn't enter an input it doesn't transfer to Suggestion-one.xml
            }

            closeBtn.setOnClickListener {
                moveTaskToBack(true)
                android.os.Process.killProcess(android.os.Process.myPid())
                exitProcess(1)
            }

            val mealSuggestion = matchTimeOfDayToMeal(timeOfDay.lowercase())

            val intent = Intent(this, Suggestionone::class.java)
            intent.putExtra("TimeOfDay", mealSuggestion)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
 // Used mapOf to store data in reference to the history application
    private fun matchTimeOfDayToMeal(time: String): String {
        val mealOptions = mapOf(
            "breakfast" to "eggs, bacon and toast",
            "Mid-morning snack" to "fruit salad",
            "lunch" to "chicken wrap with fried chips",
            "mid day snack" to "a packet of Simba chips alongside nuts",
            "dinner" to "steak with mashed potatoes",
            "After-dinner snack" to "ice cream"
        )

      // AI added return statement for the result projection and error handling message
        return mealOptions[time]?.let { "Enjoy your meal of $it." } ?: "No meal option found for this time of day."
    }
}



