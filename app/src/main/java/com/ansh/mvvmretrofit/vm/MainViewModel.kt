package com.ansh.mvvmretrofit.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansh.mvvmretrofit.models.QuoteList
import com.ansh.mvvmretrofit.repo.QuotesRepo
import com.ansh.mvvmretrofit.repo.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuotesRepo) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getQuotes(1)
        }
    }
    val quotes : LiveData<Response<QuoteList>>
    get() =repository.quotes

}