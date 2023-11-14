package com.example.group_project

import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat


class StatsAdapter(private  val statsList: List<String>,private  val stats2List: List<String>,private val teamname: String, private val teamname1: String) : RecyclerView.Adapter<StatsAdapter.ViewHolder>()
{
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val statsText1: TextView
        val statsText2: TextView
        //val teamDetailsTextView: TextView = view.findViewById(R.id.teamDetailsTextView)
        // val teamDetailsTextView1: TextView = view.findViewById(R.id.teamDetailsTextView1)


        init {
            statsText1 = view.findViewById(R.id.stats1_text)
            statsText2 = view.findViewById(R.id.stats2_text)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_team_detail,parent,false)
        return ViewHolder(view)
        Log.d("com.example.group_project.StatsAdapter", "onCreateViewHolder")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.statsText1.text = "Stats : ${statsList[position]}"
        holder.statsText2.text = "Quantity: ${stats2List[position]}"

        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.searchbarGreen))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.green2))
        }

        // holder.teamDetailsTextView1.text = teamname1

        Log.d("com.example.group_project.StatsAdapter", "onBindViewHolder position: $position, stats1: ${statsList[position]}")
    }


    override fun getItemCount() = statsList.size

}