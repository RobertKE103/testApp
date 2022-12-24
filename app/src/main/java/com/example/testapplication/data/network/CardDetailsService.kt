package com.example.testapplication.data.network

import com.example.testapplication.domain.modules.network.CardDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CardDetailsService {

    @GET
    suspend fun getDataCart(
        @Url url: String
    ): Response<CardDetails>

}