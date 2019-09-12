package com.example.rickandmortykotlin.remoteDatabase.responses

import com.example.rickandmortykotlin.models.EpisodeResults
import com.example.rickandmortykotlin.models.Info

data class Episodes(var info: Info, var results: List<EpisodeResults>)