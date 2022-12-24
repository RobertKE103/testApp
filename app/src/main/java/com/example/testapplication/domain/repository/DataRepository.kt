package com.example.testapplication.domain.repository

import androidx.lifecycle.LiveData
import com.example.testapplication.domain.modules.database.BinNumber
import com.example.testapplication.domain.modules.network.CardDetails
import retrofit2.Response

interface DataRepository {
    suspend fun getDataCart(bin: Long): Response<CardDetails>
    fun getListBin(): LiveData<List<BinNumber>>
    suspend fun saveBIN(bin: BinNumber)
    suspend fun deleteBin()

}