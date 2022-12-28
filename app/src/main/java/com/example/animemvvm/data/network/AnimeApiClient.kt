package com.example.animemvvm.data.network

import com.example.animemvvm.data.model.Animes
import retrofit2.Response
import retrofit2.http.GET

interface AnimeApiClient {
    @GET("/anime?page=1&size=100")
    suspend fun getAllAnimes(): Response<Animes>
}