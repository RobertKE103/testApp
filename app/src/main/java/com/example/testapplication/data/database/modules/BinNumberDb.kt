package com.example.testapplication.data.database.modules

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BinNumberDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val bin: Long,
)