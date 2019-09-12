package com.example.rickandmortykotlin.remoteDatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortykotlin.models.EpisodeResults
import com.example.rickandmortykotlin.remoteDatabase.responses.Episodes
import com.example.rickandmortykotlin.threads.Executors
import com.example.rickandmortykotlin.utils.Constants.NETWORK_TIMEOUT
import retrofit2.Call
import java.util.concurrent.TimeUnit

object EpisodesApiClient {

    private val nEpisodes = MutableLiveData<List<EpisodeResults>>()

    private var episodesRunnable: EpisodesRunnable? = null

    fun getEpisodes(): LiveData<List<EpisodeResults>>{
        getEpisodesApi()
        return nEpisodes
    }

    fun getEpisodesApi(){
        episodesRunnable = EpisodesRunnable()

        val handler = Executors.networkIO().submit(episodesRunnable)

        Executors.networkIO().schedule({handler.cancel(true)}, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    }

    class EpisodesRunnable: Runnable {

        var cancelRequest = false

        override fun run() {
            val response = getEpisodes().execute()

            if(cancelRequest){
                return
            }

            if(response.code() == 200){
                val list = response.body()!!.results
                nEpisodes.postValue(list)
            }
        }

        private fun getEpisodes(): Call<Episodes> {
            return ServiceGenerators.getApiClient().getEpisodes()
        }

    }
}


