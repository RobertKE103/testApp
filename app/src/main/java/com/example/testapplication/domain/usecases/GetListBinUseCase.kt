package com.example.testapplication.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.testapplication.domain.modules.database.BinNumber
import com.example.testapplication.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListBinUseCase @Inject constructor(
    private val repository: DataRepository,
) {
    operator fun invoke(): LiveData<List<BinNumber>> {
        return repository.getListBin()
    }

}