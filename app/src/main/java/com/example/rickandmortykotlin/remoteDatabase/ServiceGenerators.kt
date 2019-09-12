package com.example.rickandmortykotlin.remoteDatabase

import com.example.rickandmortykotlin.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerators {


    companion object{

        private var retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        private var retrofit = retrofitBuilder.build()

        private var rickAndMortyApiClient = retrofit.create(RickAndMortyApiClient::class.java)

        fun getApiClient(): RickAndMortyApiClient {
            return rickAndMortyApiClient
        }
    }
}