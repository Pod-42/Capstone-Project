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
        val teamId1 = intent.getIntExtra("TEAM_ID1", -1)

        val backButton: ImageView = findViewById(R.id.backButton)
        val teamDetailsTextView: TextView = findViewById(R.id.teamDetailsTextView)
        val teamDetailsTextView1: TextView = findViewById(R.id.teamDetailsTextView1)


        teamDetailsTextView.text = teamId.toString()
        teamDetailsTextView1.text = teamId1.toString()

        /*

        Note to Kemar in MainActivity2.kt
        There is 2 team ids being passed from the main_activity.kt - Home team and away team. So, you can use this team id information to do an api call and make changes in the
        main_team_details.xml file. What I was thinking was you can get the players names and images in the teams and show it in the recyclerview. If you
        have other ideas you can implement that as well.

        activity_team_detail.xml uses MainActivity.kt file
        (activity_main.xml is the recyclerview and front_page.xml) uses app_adapter.kt and MainActivity.kt

         */

        

        backButton.setOnClickListener {
            finish() // Close this activity and return to the previous one
        }

    }
}