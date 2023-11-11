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

class FixtureAdapter(private var fixtureList: List<Fixture>) : RecyclerView.Adapter<FixtureAdapter.FixtureViewHolder>() {

    fun updateFixtures(newFixtures: List<Fixture>) {
        this.fixtureList = newFixtures
        notifyDataSetChanged()
    }

    class FixtureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTeam1Name: TextView = itemView.findViewById(R.id.textView)
        val team1_logo: ImageView = itemView.findViewById(R.id.imageView)
        val team2_logo: ImageView = itemView.findViewById(R.id.imageView2)
        val tvScore: TextView = itemView.findViewById(R.id.textView2)
        val tvTeam2Name: TextView = itemView.findViewById(R.id.textView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.front_page, parent, false)
        return FixtureViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FixtureViewHolder, position: Int) {
        val currentFixture = fixtureList[position]
        holder.tvTeam1Name.text = currentFixture.teams.home.name
        holder.tvScore.text = "${currentFixture.goals.home} : ${currentFixture.goals.away}"
        holder.tvTeam2Name.text = currentFixture.teams.away.name

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorWhite))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorLightGrey))
        }

        Glide.with(holder.itemView.context)
            .load(currentFixture.teams.home.logo)
            .centerCrop()
            .into(holder.team1_logo)

        Glide.with(holder.itemView.context)
            .load(currentFixture.teams.away.logo)
            .centerCrop()
            .into(holder.team2_logo)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MainActivity2::class.java)

            intent.putExtra("TEAM_ID", currentFixture.teams.home.id)
            intent.putExtra("TEAM_ID1", currentFixture.teams.away.id)

            context.startActivity(intent)
        }
    }
    override fun getItemCount() = fixtureList.size
}
