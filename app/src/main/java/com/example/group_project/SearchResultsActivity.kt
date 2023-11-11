package com.example.group_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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

class SearchResultsActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var fixturesAdapter: SearchAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val query = intent.getStringExtra("SEARCH_QUERY")

        val backButton2: ImageView = findViewById(R.id.backButton2)

        backButton2.setOnClickListener {
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
        val recyclerView: RecyclerView = findViewById(R.id.score_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fixturesAdapter = SearchAdapter(emptyList()) // Initialize with an empty list
        recyclerView.adapter = fixturesAdapter

        recyclerView.itemAnimator = DefaultItemAnimator()
        fetchSearchData(33)
    }

    private fun fetchSearchData(teamid : Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getTeamFixtures(teamid)
                if (response.isSuccessful) {
                    val fixtures = response.body()?.response
                    Log.d("MainActivity", "API Success: $fixtures")

                    withContext(Dispatchers.Main) {
                        if (fixtures.isNullOrEmpty()) {
                            Log.d("MainActivity", "No fixtures in response")
                        } else {
                            fixturesAdapter.updateSearchResults(fixtures)
                        }
                    }
                } else {
                    Log.e("MainActivity", "API Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Exception in API call", e)
            }
        }
    }
}