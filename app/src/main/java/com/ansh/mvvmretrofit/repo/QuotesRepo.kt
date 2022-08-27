package com.ansh.mvvmretrofit.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ansh.mvvmretrofit.api.QuotesService
import com.ansh.mvvmretrofit.models.QuoteList

class QuotesRepo(private val quoteService: QuotesService) {

    private val quotesLivedata = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
    get() = quotesLivedata

    suspend fun getQuotes(page:Int){
        val result = quoteService.getQuotes(page)
        if(result ?.body()!=null ){

            quotesLivedata.postValue(result.body())
        }
    }

}