package com.example.mealapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputEditText: EditText
    private lateinit var generateButton: Button
    private lateinit var closeBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inputEditText = findViewById(R.id.inputEditText)
        generateButton = findViewById(R.id.generateBtn)
       closeBtn = findViewById(R.id.closeBtn)


        generateButton.setOnClickListener {
            val timeOfDay = inputEditText.text.toString().trim()

            if (timeOfDay.isEmpty()) {
                Toast.makeText(this, "Enter Time Of Day Input Please!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            closeBtn.setOnClickListener {
                finishAffinity()
            }

            val mealSuggestion = matchDayTypeToMealSuggestion(timeOfDay.lowercase())

            val intent = Intent(this, Suggestionone::class.java)
            intent.putExtra("DayType", mealSuggestion)
            startActivity(intent)
        }

    }
 // Used mapOf to store data in reference to the history application
    private fun matchDayTypeToMealSuggestion (time: String): String {
        val mealSuggestion = mapOf(
            "breakfast" to "toast with jam",
            "Mid-morning snack" to "packet of dried fruit",
            "lunch" to "chicken and cabbage wrap ",
            "mid day snack" to "a packet of Simba chips ",
            "dinner" to "steak with mashed potatoes",
            "After-dinner snack" to "ice cream"
        )
     
        return mealSuggestion[time]?.let { "Enjoy your meal of $it." } ?: "No meal option found for this time of day."
    }
}



