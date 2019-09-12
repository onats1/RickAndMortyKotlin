package com.example.rickandmortykotlin.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortykotlin.models.EpisodeResults
import com.example.rickandmortykotlin.repositories.AppRepository

class EpisodeViewModel: ViewModel() {

    fun getAllEpisodes(): LiveData<List<EpisodeResults>>{
        return AppRepository.getAllEpisodes()
    }
}