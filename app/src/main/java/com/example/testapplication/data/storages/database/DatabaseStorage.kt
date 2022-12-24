package com.example.testapplication.data.storages.database

import androidx.lifecycle.LiveData
import com.example.testapplication.domain.modules.database.BinNumber

interface DatabaseStorage {

    suspend fun deleteBin()

    suspend fun saveBin(bin: BinNumber)

    fun getListBin(): LiveData<List<BinNumber>>

}