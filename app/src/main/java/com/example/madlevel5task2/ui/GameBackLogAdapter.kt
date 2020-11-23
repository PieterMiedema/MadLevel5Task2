package com.example.madlevel5task2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.model.Game
import kotlinx.android.synthetic.main.item_game.view.*


class GameBacklogAdapter(private val gameHistory: List<Game>) : RecyclerView.Adapter<GameBacklogAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        )
    }

    override fun getItemCount(): Int = gameHistory.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(gameHistory[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(game: Game) {
            itemView.item_title.text = game.title
            itemView.item_os.text = game.platform
            itemView.item_release_date.text =
                context.getString(R.string.release_date, Utils.dateToString(game.release.time))
        }
    }
}