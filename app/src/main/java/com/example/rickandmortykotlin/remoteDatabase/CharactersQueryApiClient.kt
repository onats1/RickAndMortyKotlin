package com.example.rickandmortykotlin.remoteDatabase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickandmortykotlin.remoteDatabase.responses.Characters
import com.example.rickandmortykotlin.threads.Executors
import com.example.rickandmortykotlin.utils.Constants
import com.example.rickandmortykotlin.utils.Constants.NETWORK_TIMEOUT
import com.example.rickandmortykotlin.utils.Constants.SORT_GENDER_FEMALE
import com.example.rickandmortykotlin.utils.Constants.SORT_GENDER_GENDERLESS
import com.example.rickandmortykotlin.utils.Constants.SORT_GENDER_MALE
import com.example.rickandmortykotlin.utils.Constants.SORT_STATUS_ALIVE
import com.example.rickandmortykotlin.utils.Constants.SORT_STATUS_DEAD
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.TimeUnit

object CharactersQueryApiClient {
    private var nCharacters: MutableLiveData<List<Characters>>
    private var retrieveCharactersRunnable: RetrieveCharactersrunnable? = null
    private lateinit var characterIds: Array<Int>
    private lateinit var order: String

    init {
        nCharacters = MutableLiveData()
    }

    fun getCharacters(): LiveData<List<Characters>> {
        return nCharacters
    }

    fun setGender(characters: Array<Int>, order: String) {
        characterIds = characters
        this.order = when (order) {
            SORT_GENDER_FEMALE -> SORT_GENDER_FEMALE
            SORT_GENDER_MALE -> SORT_GENDER_MALE
            SORT_GENDER_GENDERLESS -> SORT_GENDER_GENDERLESS
            else -> SORT_GENDER_GENDERLESS
        }
    }

    fun setStatus(characters: Array<Int>, order: String) {
        characterIds = characters
        this.order = when (order) {
            SORT_STATUS_ALIVE -> SORT_STATUS_ALIVE
            SORT_STATUS_DEAD -> SORT_STATUS_DEAD
            else -> SORT_STATUS_ALIVE
        }
    }

    private fun getCharacterByGender(): Call<List<Characters>> {
        return ServiceGenerators.getApiClient().getCharactersByGender(characterIds, order)
    }

    private fun getCharacterByStatus(): Call<List<Characters>> {
        return ServiceGenerators.getApiClient().getCharacterByStatus(characterIds, order)

    }

    private fun getCharacterByOrder(order: String) = when (order) {

        SORT_GENDER_MALE -> getCharacterByGender()
        SORT_GENDER_FEMALE -> getCharacterByGender()
        SORT_GENDER_GENDERLESS -> getCharacterByGender()
        SORT_STATUS_ALIVE -> getCharacterByStatus()
        SORT_STATUS_DEAD -> getCharacterByStatus()
        else -> null

    }

    fun getCharactersApi() {
        retrieveCharactersRunnable = RetrieveCharactersrunnable(order)
        val handler = Executors.networkIO().submit(retrieveCharactersRunnable!!)

        Executors.networkIO().schedule({
            handler.cancel(true)
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
    }


    class RetrieveCharactersrunnable(order: String) : Runnable {

        var cancelRequest: Boolean = false
        var order: String? = null

        init {
            this.order = when (order) {
                SORT_GENDER_FEMALE -> SORT_GENDER_FEMALE
                SORT_GENDER_MALE -> SORT_GENDER_MALE
                SORT_GENDER_GENDERLESS -> SORT_GENDER_GENDERLESS
                SORT_STATUS_ALIVE -> SORT_STATUS_ALIVE
                SORT_STATUS_DEAD -> SORT_STATUS_DEAD
                else -> null
            }
        }

        override fun run() {

            var response = getCharacterByOrder(order!!)?.execute()

            if (cancelRequest) {
                return
            }

            response!!.let {
                if (response.code() == 200) {
                    var list = response.body()
                    nCharacters.postValue(list)
                }
            }

        }

        fun cancelRequest(){
            cancelRequest = true
        }
    }
}