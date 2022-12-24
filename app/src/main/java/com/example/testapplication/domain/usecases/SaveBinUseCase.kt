package com.example.testapplication.domain.usecases

import com.example.testapplication.domain.modules.database.BinNumber
import com.example.testapplication.domain.repository.DataRepository
import javax.inject.Inject

class SaveBinUseCase @Inject constructor(
    private val repository: DataRepository
) {
    suspend operator fun invoke(bin: BinNumber){
        return repository.saveBIN(bin)
    }
}