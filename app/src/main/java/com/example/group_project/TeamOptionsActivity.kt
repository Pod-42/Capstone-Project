package com.example.group_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/*
feature for searchbar
This kotlin file works on the feature when the user searches for the team
 */
class TeamOptionsActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var teamAdapter: TeamsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val query = intent.getStringExtra("SEARCH_QUERY")

        val backButton3: ImageView = findViewById(R.id.backButton3)

        backButton3.setOnClickListener {
            finish() // Close this activity and return to the previous one
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-football-v1.p.rapidapi.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.team_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        teamAdapter = TeamsListAdapter(emptyList()) // Initialize with an empty list
        recyclerView.adapter = teamAdapter

        recyclerView.itemAnimator = DefaultItemAnimator()
        if (query != null) {
            fetchTeamData(query)
        }
    }

    private fun fetchTeamData(teamName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.searchTeam(teamName)
                if (response.isSuccessful) {
                    // Assuming 'response' is of type TeamList as defined earlier
                    val teamList = response.body()
                    val teams = teamList?.response?.map { it.team } ?: emptyList()
                    Log.d("TeamOptionsActivity", "API Success: $teams")

                    withContext(Dispatchers.Main) {
                        if (teams.isEmpty()) {
                            Log.d("TeamOptionsActivity", "No teams found")
                        } else {
                            teamAdapter.updateTeams(teams)
                        }
                    }
                } else {
                    Log.e("TeamOptionsActivity", "API Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("TeamOptionsActivity", "Exception in API call", e)
            }
        }
    }

}