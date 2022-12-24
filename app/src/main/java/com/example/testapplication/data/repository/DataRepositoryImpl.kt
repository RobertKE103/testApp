package com.example.testapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.testapplication.data.storages.database.DatabaseStorage
import com.example.testapplication.data.storages.network.NetworkStorage
import com.example.testapplication.domain.modules.database.BinNumber
import com.example.testapplication.domain.modules.network.CardDetails
import com.example.testapplication.domain.repository.DataRepository
import retrofit2.Response

class DataRepositoryImpl(
    private val networkStorage: NetworkStorage,
    private val databaseStorage: DatabaseStorage
): DataRepository {

    override suspend fun getDataCart(bin: Long): Response<CardDetails> {
        return networkStorage.getCardDetails(bin)
    }

    override fun getListBin(): LiveData<List<BinNumber>> {
        return databaseStorage.getListBin()
    }

    override suspend fun saveBIN(bin: BinNumber) {
        return databaseStorage.saveBin(bin)
    }

    override suspend fun deleteBin() {
        return databaseStorage.deleteBin()
    }

}