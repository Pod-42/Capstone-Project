package com.example.group_project
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Query

interface ApiService {
    @GET("v3/fixtures?live=all")
    suspend fun getLiveFixtures(): Response<FixtureResponse>

    @GET("v3/fixtures")
    suspend fun getTeamFixtures(@Query("team") teamId: Int): Response<FixtureResponse>

}

