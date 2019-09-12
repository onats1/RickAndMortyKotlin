package com.example.rickandmortykotlin.repositories

import androidx.lifecycle.LiveData
import com.example.rickandmortykotlin.models.EpisodeResults
import com.example.rickandmortykotlin.remoteDatabase.CharactersApiClient
import com.example.rickandmortykotlin.remoteDatabase.EpisodesApiClient
import com.example.rickandmortykotlin.remoteDatabase.responses.Characters

object AppRepository {


    fun getAllEpisodes(): LiveData<List<EpisodeResults>>{
        return EpisodesApiClient.getEpisodes()
    }

    fun getEpisodeCharacters(characterUrls: List<String>): LiveData<ArrayList<Characters?>> {
       return CharactersApiClient.apply { this.characterUrls = characterUrls }.getCharacters()
    }



}