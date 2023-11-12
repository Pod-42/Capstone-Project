package com.example.group_project

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import org.json.JSONException
import java.io.IOException


class MainActivity2 : AppCompatActivity() {
    private lateinit var stats1List: MutableList<String>
    private lateinit var stats2List: MutableList<String>
    private lateinit var rvStats1: RecyclerView
    private var teamId: Int = -1
    private var teamId1: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        stats1List = mutableListOf()
        stats2List = mutableListOf()
        rvStats1 = findViewById(R.id.stats_list)
//        val teamDetailsTextView: TextView = findViewById(R.id.teamDetailsTextView)
//        val teamDetailsTextView1: TextView = findViewById(R.id.teamDetailsTextView1)

        // Retrieve team IDs from intent
        teamId = intent.getIntExtra("TEAM_ID", -1)
        teamId1 = intent.getIntExtra("TEAM_ID1", -1)

        // Set text in TextViews
//        teamDetailsTextView.text = teamId.toString()
//        teamDetailsTextView1.text = teamId1.toString()

        getMatchStats()

//        backButton.setOnClickListener {
//            finish() // Close this activity and return to the previous one
//        }
    }

    private fun getMatchStats() {
        val client = OkHttpClient()

        // Use teamId and teamId1 in the API URL
        val request = Request.Builder()
            .url("https://api-football-v1.p.rapidapi.com/v3/fixtures/statistics?fixture=215662&team=463")
            .get()
            .addHeader("X-RapidAPI-Key", "18f06aa827msh381ea3eadf3ea75p1828c3jsne7f3ec96a4a0")
            .addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle failure
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseData = response.body?.string()
                // Process the response data (responseData) here
                // Update UI or perform other actions based on the response data
                processResponseData(responseData)

                Log.d("Response Data", responseData ?: "Response Data is null")
            }

        })

    }
    private fun processResponseData(responseData: String?) {
        try {
            // Assuming the root is an object
            val jsonObject = JSONObject(responseData)

            // Access the "response" array
            val responseArray = jsonObject.getJSONArray("response")

            // Access other values as needed
            for (i in 0 until responseArray.length()) {
                val statsArray = responseArray.getJSONObject(i).getJSONArray("statistics")

                for (j in 0 until statsArray.length()) {
                    val stats = statsArray.getJSONObject(j)
                    // Access individual statistics here
                    val type = stats.getString("type")
                    val value = stats.getString("value")
                    stats1List.add(type)
                    // Inside processResponseData after updating stats1List and stats2List
                    Log.d("List size", "stats1List size: ${stats1List.size}")


                    // Log or process the individual statistics as needed
                    Log.d("List league", "Type: $type, Value: $value")
                }
            }

            // Update the UI (RecyclerView) with the stats lists
            runOnUiThread {

                val adapter = StatsAdapter(stats1List)
                rvStats1.layoutManager = LinearLayoutManager(this@MainActivity2)
                rvStats1.adapter = adapter
            }

        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("List league", "Error in processResponseData: ${e.message}")
        }
    }

}
