package com.example.group_project

data class TeamList(
    val response: List<ResponseItem>
)
data class ResponseItem(
    val team: FootballTeam
)

data class FootballTeam(
    val id: Int,
    val logo: String?,
    val name: String
)


