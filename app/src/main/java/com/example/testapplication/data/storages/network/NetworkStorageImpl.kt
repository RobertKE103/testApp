package com.example.testapplication.data.storages.network

import androidx.lifecycle.LiveData
import com.example.testapplication.data.network.CardDetailsService
import com.example.testapplication.domain.modules.network.CardDetails
import retrofit2.Response

class NetworkStorageImpl(
    private val cardDetailsService: CardDetailsService
): NetworkStorage {
    override suspend fun getCardDetails(bin: Long): Response<CardDetails> {
        val url = "https://lookup.binlist.net/${bin}"
        return cardDetailsService.getDataCart(url)
    }


}