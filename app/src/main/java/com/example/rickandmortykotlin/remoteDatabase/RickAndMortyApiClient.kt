package com.example.rickandmortykotlin.remoteDatabase

import com.example.rickandmortykotlin.remoteDatabase.responses.Characters
import com.example.rickandmortykotlin.remoteDatabase.responses.Episodes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface RickAndMortyApiClient {

    @Headers("Content-Type: application/json")
    @GET("/api/episode/")
    fun getEpisodes(): Call<Episodes>

    @GET
    fun getCharacter(@Url url: String): Call<Characters>

    @GET("/api/character/")
    fun getCharactersByGender(
        @Query("") characterIds: Array<Int>,
        @Query("gender") gender: String
    ): Call<List<Characters>>

    @GET("/api/character/")
    fun getCharacterByStatus(
        @Query("") characterIds: Array<Int>,
        @Query("status") status: String
    ): Call<List<Characters>>


}