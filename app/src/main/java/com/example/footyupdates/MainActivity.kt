package com.example.footyupdates

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Assuming you have a SearchView with ID "searchView" in your layout
        val searchView = findViewById<SearchView>(R.id.searchView)

        // Set an OnClickListener for the search icon to initiate the navigation
        searchView.setOnSearchClickListener { // Initiate the navigation to the new activity
            val intent = Intent(this@MainActivity, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}


