package com.ansh.mvvmretrofit.api

import com.ansh.mvvmretrofit.models.QuoteList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesService {

    @GET("quotes")
    suspend fun getQuotes(@Query("page")page:Int): Response<QuoteList>

    //Base URl + "/quotes?page=1
}