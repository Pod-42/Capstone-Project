package com.example.group_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        val teamId = intent.getIntExtra("TEAM_ID", -1)

        val backButton: ImageView = findViewById(R.id.backButton)
        val teamDetailsTextView: TextView = findViewById(R.id.teamDetailsTextView)

        teamDetailsTextView.text = teamId.toString()

        backButton.setOnClickListener {
            finish() // Close this activity and return to the previous one
        }

    }
}