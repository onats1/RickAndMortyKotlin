package com.example.rickandmortykotlin.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortykotlin.R
import com.example.rickandmortykotlin.adapters.EpisodesListener
import com.example.rickandmortykotlin.adapters.EpisodesRecyclerAdapter
import com.example.rickandmortykotlin.models.EpisodeResults
import com.example.rickandmortykotlin.viewModels.EpisodeViewModel
import kotlinx.android.synthetic.main.activity_main.*

class EpisodesActivity : AppCompatActivity(), EpisodesListener {


    var episodeResults: List<EpisodeResults>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(EpisodeViewModel::class.java)

        var recyclerAdapter: EpisodesRecyclerAdapter? = null

        viewModel.getAllEpisodes().observe(this,
            Observer { episodes ->

                recyclerAdapter = EpisodesRecyclerAdapter(this).apply {
                    episodesList = episodes
                }

                episode_recycler.adapter = recyclerAdapter
                episode_recycler.layoutManager = LinearLayoutManager(this)
            })


    }

    private fun observers() {

    }

    override fun onEpisodesClick(position: Int) {

    }
}
