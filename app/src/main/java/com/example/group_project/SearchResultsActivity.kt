package com.example.group_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class SearchResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)
        val query = intent.getStringExtra("SEARCH_QUERY")

        val backButton2: ImageView = findViewById(R.id.backButton2)

        backButton2.setOnClickListener {
            finish() // Close this activity and return to the previous one
        }
    }
}