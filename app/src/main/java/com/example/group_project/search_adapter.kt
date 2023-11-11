package com.example.group_project
import android.animation.Animator
import android.animation.ValueAnimator
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.core.content.ContextCompat

class SearchAdapter(private var searchResults: List<Fixture>) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    fun updateSearchResults(newResults: List<Fixture>) {
        this.searchResults = newResults
        notifyDataSetChanged()
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTeam1Name: TextView = itemView.findViewById(R.id.search_textView)
        val team1Logo: ImageView = itemView.findViewById(R.id.search_imageView1)
        val team2Logo: ImageView = itemView.findViewById(R.id.search_imageView2)
        val tvScore: TextView = itemView.findViewById(R.id.search_textView2)
        val tvTeam2Name: TextView = itemView.findViewById(R.id.search_textView3)
        val name: TextView = itemView.findViewById(R.id.search_textView4)
        val season: TextView = itemView.findViewById(R.id.search_textView6)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_search_results, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentResult = searchResults[position]
        holder.tvTeam1Name.text = currentResult.teams.home.name
        holder.tvScore.text = "${currentResult.goals.home} : ${currentResult.goals.away}"
        holder.tvTeam2Name.text = currentResult.teams.away.name
        holder.name.text = currentResult.league.name
        holder.season.text = "Season : ${currentResult.league.season.toString()}"

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorWhite))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorLightGrey))
        }

        Glide.with(holder.itemView.context)
            .load(currentResult.teams.home.logo)
            .centerCrop()
            .into(holder.team1Logo)

        Glide.with(holder.itemView.context)
            .load(currentResult.teams.away.logo)
            .centerCrop()
            .into(holder.team2Logo)

        // Set up any click listener if needed
    }

    override fun getItemCount() = searchResults.size
}
