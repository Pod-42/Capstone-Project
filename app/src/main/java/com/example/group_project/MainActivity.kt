package com.example.group_project
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.inputmethod.InputMethodManager
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


class MainActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var fixturesAdapter: FixtureAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView: SearchView = findViewById(R.id.search1)
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // User pressed the search button
                if (query != null) {
                    val intent = Intent(this@MainActivity, TeamOptionsActivity::class.java)
                    intent.putExtra("SEARCH_QUERY", query)
                    startActivity(intent)
                }
                val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(searchView.windowToken, 0)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Search query text has changed
                return true
            }
        })

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-football-v1.p.rapidapi.com/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.score_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fixturesAdapter = FixtureAdapter(emptyList()) // Initialize with an empty list
        recyclerView.adapter = fixturesAdapter

        recyclerView.itemAnimator = DefaultItemAnimator()
        fetchLiveFixtures()
    }


    private fun fetchLiveFixtures() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getLiveFixtures()
                if (response.isSuccessful) {
                    val fixtures = response.body()?.response
                    Log.d("MainActivity", "API Success: $fixtures")

                    withContext(Dispatchers.Main) {
                        if (fixtures.isNullOrEmpty()) {
                            Log.d("MainActivity", "No fixtures in response")
                        } else {
                            fixturesAdapter.updateFixtures(fixtures)
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