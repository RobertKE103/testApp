package com.example.testapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapplication.data.database.modules.BinNumberDb

@Database(entities = [BinNumberDb::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun binDao(): BinDao
}