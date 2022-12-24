package com.example.testapplication.domain.usecases

import com.example.testapplication.domain.modules.network.CardDetails
import com.example.testapplication.domain.repository.DataRepository
import retrofit2.Response
import javax.inject.Inject

class GetDataCartUseCase @Inject constructor(
    private val repository: DataRepository
) {

    suspend operator fun invoke(bin: Long): Response<CardDetails> {
        return repository.getDataCart(bin)
    }

}