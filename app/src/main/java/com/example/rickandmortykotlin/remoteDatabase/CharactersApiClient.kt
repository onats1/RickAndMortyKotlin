package com.example.rickandmortykotlin.remoteDatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortykotlin.remoteDatabase.responses.Characters
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object CharactersApiClient {

    lateinit var characterUrls: List<String>
    val nCharacters: MutableLiveData<ArrayList<Characters?>>

    init {
        nCharacters = MutableLiveData()
    }

    fun getCharacters(): LiveData<ArrayList<Characters?>> { return nCharacters }



    fun getCharacter(url: String): Call<Characters> {
        return ServiceGenerators.getApiClient().getCharacter(url)
    }

    fun getAllCharacters(urlList: List<String>) {
        var list: ArrayList<Characters?> = arrayListOf()

        for (item in urlList) {
            val call = getCharacter(item)

            call.enqueue(object : Callback<Characters> {
                override fun onFailure(call: Call<Characters>, t: Throwable) {

                }

                override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                    response.body()?.let {
                        list.add(response.body())
                    }
                }
            })
        }

        nCharacters.postValue(list)
    }

}

