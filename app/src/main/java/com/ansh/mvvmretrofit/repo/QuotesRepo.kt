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

    private val quotesLivedata = MutableLiveData<Response<QuoteList>>()

    val quotes: LiveData<Response<QuoteList>>
        get() = quotesLivedata

    suspend fun getQuotes(page: Int) {



        if (NetworkUtils.isNetworkAvailable(applicationContext)) {

            try {

                val result = quoteService.getQuotes(page)
                if (result.body() != null) {

                    quoteDatabase.quoteDao().addQuotes(result.body()!!.results)
                    quotesLivedata.postValue(Response.Success(result.body()))
                }

            }catch(e :Exception){

                quotesLivedata.postValue(Response.Error(e.message.toString()))

            }


            } else {

                val quotes = quoteDatabase.quoteDao().getQuotes()
                val quoteList = QuoteList(1, 1, 1, quotes, 1, 1)
                quotesLivedata.postValue(Response.Success(quoteList))
            }



    }
    suspend fun getQuotesBackground() {
        val randomNumber = (Math.random() * 10).toInt()
        val result = quoteService.getQuotes(randomNumber)
        if (result.body() != null) {

        }
    }

}