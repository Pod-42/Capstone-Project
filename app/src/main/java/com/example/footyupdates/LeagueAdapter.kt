package com.example.footyupdates

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LeagueAdapter(private  val leagueList: List<String>) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>()
{
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val leagueImg: ImageView

        init {
            leagueImg = view.findViewById(R.id.league_img)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_info,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeagueAdapter.ViewHolder, position: Int) {
        val imageUrl = leagueList[position]
        Log.d("Image URL",imageUrl)
        Glide.with(holder.itemView)
            .load(imageUrl)
            .centerCrop()
            .into(holder.leagueImg)
    }

    override fun getItemCount() = leagueList.size

}