package com.example.testapplication.domain.usecases

import com.example.testapplication.domain.repository.DataRepository
import javax.inject.Inject

class DeleteBinUseCase @Inject constructor(
    private val repository: DataRepository,
) {
    suspend operator fun invoke() {
        return repository.deleteBin()
    }
}