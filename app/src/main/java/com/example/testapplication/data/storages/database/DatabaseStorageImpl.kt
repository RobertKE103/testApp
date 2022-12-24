package com.example.testapplication.data.storages.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.testapplication.data.database.BinDao
import com.example.testapplication.data.database.modules.BinNumberDb
import com.example.testapplication.data.mappers.Mapper
import com.example.testapplication.domain.modules.database.BinNumber
import javax.inject.Inject

class DatabaseStorageImpl @Inject constructor(
    private val binDao: BinDao,
    private val mapper: Mapper
) : DatabaseStorage {
    override suspend fun deleteBin() {
        return binDao.deleteBin()
    }

    override suspend fun saveBin(bin: BinNumber) {
        val binDb = BinNumberDb(bin = bin.bin)
        return binDao.saveBin(binDb)
    }

    override fun getListBin(): LiveData<List<BinNumber>> =
        MediatorLiveData<List<BinNumber>>().apply {
            addSource(binDao.getListBin()){
                value = mapper.listBinDbToUi(it)
            }
        }


}