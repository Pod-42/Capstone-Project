package com.example.group_project

import android.util.Log

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

class StatsAdapter(private  val statsList: List<String>) : RecyclerView.Adapter<StatsAdapter.ViewHolder>()
{
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val statsText1: TextView


        init {
            statsText1 = view.findViewById(R.id.stats1_text)
            val teamDetailsTextView: TextView = view.findViewById(R.id.teamDetailsTextView)
            val teamDetailsTextView1: TextView = view.findViewById(R.id.teamDetailsTextView1)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_team_detail,parent,false)
        return ViewHolder(view)
        Log.d("StatsAdapter", "onCreateViewHolder")
    }

    override fun onBindViewHolder(holder: StatsAdapter.ViewHolder, position: Int) {

        holder.statsText1.text = "Stats 1: ${statsList[position]}"
        Log.d("StatsAdapter", "onBindViewHolder position: $position, stats1: ${statsList[position]}")
    }


    override fun getItemCount() = statsList.size

}