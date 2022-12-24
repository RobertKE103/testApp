package com.example.testapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapplication.data.database.modules.BinNumberDb

@Dao
interface BinDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBin(bin: BinNumberDb)

    @Query("DELETE FROM BinNumberDb")
    suspend fun deleteBin()

    @Query("SELECT * FROM BinNumberDb")
    fun getListBin(): LiveData<List<BinNumberDb>>

}