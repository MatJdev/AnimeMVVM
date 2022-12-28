package com.example.animemvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.animemvvm.data.model.Animes
import com.example.animemvvm.data.network.AnimeApiClient

class AnimeRepository (private val AnimeApiCLient: AnimeApiClient) {

    private val animesLiveData = MutableLiveData<Animes>()

    val animes : LiveData<Animes>
    get() = animesLiveData

    suspend fun getAnimes() {
        val result = AnimeApiCLient.getAllAnimes()
        if (result.body() != null) {
            animesLiveData.postValue(result.body())
        }
    }
}