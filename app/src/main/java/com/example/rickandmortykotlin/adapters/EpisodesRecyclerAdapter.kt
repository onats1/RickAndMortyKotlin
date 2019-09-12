package com.example.rickandmortykotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortykotlin.R
import com.example.rickandmortykotlin.adapters.EpisodesRecyclerAdapter.*
import com.example.rickandmortykotlin.models.EpisodeResults
import kotlinx.android.synthetic.main.episode_item_view.view.*

class EpisodesRecyclerAdapter(var onEpisodesListener: EpisodesListener): RecyclerView.Adapter<EpisodesViewHolder>() {

    var episodesList: List<EpisodeResults>? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.episode_item_view, parent, false)
        return EpisodesViewHolder(itemView, onEpisodesListener)
    }

    override fun getItemCount(): Int {
        episodesList?.let {
            return episodesList!!.size
        }


        return 0
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.itemView.episode_title.text = episodesList!![position].episode
        holder.itemView.episode_number.text = "${episodesList!![position].id}"
    }

    inner class EpisodesViewHolder(itemView: View, var episodesListener: EpisodesListener)
        : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        override fun onClick(p0: View?) {
            episodesListener.onEpisodesClick(adapterPosition)
        }


    }
}