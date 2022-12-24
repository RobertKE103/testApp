package com.example.testapplication.data.storages.network

import com.example.testapplication.domain.modules.network.CardDetails
import retrofit2.Response

interface NetworkStorage {
    suspend fun getCardDetails(bin: Long): Response<CardDetails>
}