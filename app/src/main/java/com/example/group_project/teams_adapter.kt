package com.example.group_project

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TeamsListAdapter(private var teams: List<FootballTeam>) : RecyclerView.Adapter<TeamsListAdapter.TeamViewHolder>() {

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val teamNameTextView: TextView = view.findViewById(R.id.team_textView)
        val teamImageView: ImageView = view.findViewById(R.id.team_imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_team_options, parent, false)
        return TeamViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val currentTeam = teams[position]
        holder.teamNameTextView.text = currentTeam.name

        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.searchbarGreen))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green2))
        }

        Glide.with(holder.itemView.context)
            .load(currentTeam.logo)
            .centerCrop()
            .into(holder.teamImageView)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, SearchResultsActivity::class.java)
            intent.putExtra("TEAM_ID", currentTeam.id.toString())

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    fun updateTeams(newTeams: List<FootballTeam>) {
        teams = newTeams
        notifyDataSetChanged()
    }
}
