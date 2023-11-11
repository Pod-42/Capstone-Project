package com.example.footyupdates

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import org.json.JSONException
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    private lateinit var countryList: MutableList<String>
    private lateinit var rvLeague: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        countryList = mutableListOf()
        rvLeague = findViewById(R.id.country_list)

        // Call the function to get league data
        getLeagueData()
    }

    private fun getLeagueData() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api-football-v1.p.rapidapi.com/v3/leagues?country=England")
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
            }

        })

    }

    private fun processResponseData(responseData: String?) {
        try {
            // Assuming the root is an object
            val jsonObject = JSONObject(responseData)

            // Access other values as needed
            val leagueArray = jsonObject.getJSONArray("response")
            Log.d("League info", leagueArray.toString())

            for (i in 0 until leagueArray.length()) {
                val league = leagueArray.getJSONObject(i).getJSONObject("league")
                val logo = league.getString("logo")

                countryList.add(logo)
                Log.d("List league", logo)
            }

            // Update the UI (RecyclerView) with the countryList
            runOnUiThread {
                val adapter = LeagueAdapter(countryList)
                rvLeague.layoutManager = LinearLayoutManager(this@MainActivity2)
                rvLeague.adapter = adapter
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}
