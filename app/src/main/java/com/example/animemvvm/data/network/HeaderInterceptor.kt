package com.example.animemvvm.data.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-RapidAPI-Key", "YOUR-API-KEY") //Aquí va tu apikey si no lo cambias no funcionará la petición a la api
            .addHeader("X-RapidAPI-Host", "anime-db.p.rapidapi.com")
            .build()

        //val response = client.newCall(request).execute()
        return chain.proceed(request)
    }
}