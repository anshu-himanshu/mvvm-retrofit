package com.ansh.mvvmretrofit

import android.app.Application
import com.ansh.mvvmretrofit.api.QuotesService
import com.ansh.mvvmretrofit.api.RetrofitHelper
import com.ansh.mvvmretrofit.db.QuoteDatabase
import com.ansh.mvvmretrofit.repo.QuotesRepo

class QuoteApplication: Application() {

    lateinit var quotesRepo: QuotesRepo

    override fun onCreate() {

        super.onCreate()
        initialize()
    }

    private fun initialize() {
        val quotesService = RetrofitHelper.getInstance().create(QuotesService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext  )
        quotesRepo = QuotesRepo(quotesService,database,applicationContext)
    }
}