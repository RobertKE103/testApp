package com.example.testapplication.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.domain.modules.database.BinNumber
import com.example.testapplication.domain.modules.network.CardDetails
import com.example.testapplication.domain.usecases.DeleteBinUseCase
import com.example.testapplication.domain.usecases.GetDataCartUseCase
import com.example.testapplication.domain.usecases.GetListBinUseCase
import com.example.testapplication.domain.usecases.SaveBinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val deleteBinUseCase: DeleteBinUseCase,
    getListBinUseCase: GetListBinUseCase,
    private val saveBinUseCase: SaveBinUseCase,
    private val getDataCartUseCase: GetDataCartUseCase,
) : ViewModel() {

    val listBin: LiveData<List<BinNumber>> = getListBinUseCase.invoke()

    private val _cardData = MutableLiveData<Response<CardDetails>>()
    val cardData: LiveData<Response<CardDetails>> get() = _cardData

    private val _historyBin = MutableLiveData<MutableList<Long>>()
    val historyBi: LiveData<MutableList<Long>> get() = _historyBin

    private val _errorEnterBin = MutableLiveData<Boolean>()
    val errorEnterBin: LiveData<Boolean> get() = _errorEnterBin



    fun checkBin(bin: String){

        if (bin.length != 8){
            _errorEnterBin.value = true
        }
        else {
            viewModelScope.launch {
                saveBinUseCase.invoke(BinNumber(bin.toLong()))
                _cardData.value = getDataCartUseCase.invoke(bin.toLong())
            }
            _errorEnterBin.value = false
            addBinInHistory(bin.toLong())

        }
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }

    private fun addBinInHistory(bin: Long) {
        _historyBin.value?.add(bin)
        _historyBin.value = _historyBin.value
    }

    fun deleteBin() {
        viewModelScope.launch {
            deleteBinUseCase.invoke()
        }
    }

}