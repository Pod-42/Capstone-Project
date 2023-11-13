import com.example.group_project.R

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

class StatsAdapter(private  val statsList: List<String>,private  val stats2List: List<String>,private val teamname: String, private val teamname1: String) : RecyclerView.Adapter<StatsAdapter.ViewHolder>()
{
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val statsText1: TextView
        val statsText2: TextView
        val teamDetailsTextView: TextView = view.findViewById(R.id.teamDetailsTextView)
        // val teamDetailsTextView1: TextView = view.findViewById(R.id.teamDetailsTextView1)


        init {
            statsText1 = view.findViewById(R.id.stats1_text)
            statsText2 = view.findViewById(R.id.stats2_text)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_team_detail,parent,false)
        return ViewHolder(view)
        Log.d("StatsAdapter", "onCreateViewHolder")
    }

    override fun onBindViewHolder(holder: StatsAdapter.ViewHolder, position: Int) {

        holder.statsText1.text = "Stats : ${statsList[position]}"
        holder.statsText2.text = "Quality: ${stats2List[position]}"
        holder.teamDetailsTextView.text = "Team Name: $teamname"

        // holder.teamDetailsTextView1.text = teamname1

        Log.d("StatsAdapter", "onBindViewHolder position: $position, stats1: ${statsList[position]}")
    }


    override fun getItemCount() = statsList.size

}