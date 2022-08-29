package com.ansh.mvvmretrofit.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ansh.mvvmretrofit.api.QuotesService
import com.ansh.mvvmretrofit.db.QuoteDatabase
import com.ansh.mvvmretrofit.models.QuoteList
import com.ansh.mvvmretrofit.utils.NetworkUtils

class QuotesRepo(
    private val quoteService: QuotesService,
    private val quoteDatabase: QuoteDatabase,
    private val applicationContext: Context
) {

    private val quotesLivedata = MutableLiveData<QuoteList>()

    val quotes: LiveData<QuoteList>
    get() = quotesLivedata

    suspend fun getQuotes(page:Int){

        if (NetworkUtils.isNetworkAvailable(applicationContext)){

            val result = quoteService.getQuotes(page)
            if(result ?.body()!=null ){

                quoteDatabase.quoteDao()

                quotesLivedata.postValue(result.body())
        }else{

            val quotes = quoteDatabase.quoteDao().getQuotes()
                val quoteList =QuoteList(1,1,1,quotes,1,1)
                quotesLivedata.postValue(quoteList)
        }


        }
    }

}