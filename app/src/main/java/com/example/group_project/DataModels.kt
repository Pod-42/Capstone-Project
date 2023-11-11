package com.example.group_project
import android.os.Parcelable

data class FixtureResponse(
    val response: List<Fixture>
)

data class Fixture(
    var isExpanded: Boolean = false,
    val fixture: FixtureDetails,
    val league: League,
    val teams: Teams,
    val goals: Goals
)

data class FixtureDetails(
    val id: Int,
    val referee: String?,
)

data class League(
    val id: Int,
    val name: String,
)

data class Teams(
    val home: Team,
    val away: Team
)

data class Team(
    val id: Int,
    val name: String,
    val logo: String,
    val winner: Boolean?
)

data class Goals(
    val home: Int,
    val away: Int
)



