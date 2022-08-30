package com.ansh.mvvmretrofit

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.ansh.mvvmretrofit.api.QuotesService
import com.ansh.mvvmretrofit.api.RetrofitHelper
import com.ansh.mvvmretrofit.db.QuoteDatabase
import com.ansh.mvvmretrofit.repo.QuotesRepo
import com.ansh.mvvmretrofit.worker.QuoteWorker
import java.util.concurrent.TimeUnit

class QuoteApplication: Application() {

    lateinit var quotesRepo: QuotesRepo

    override fun onCreate() {

        super.onCreate()
        initialize()
        setupWorker()
    }

    private fun setupWorker() {
       val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest = PeriodicWorkRequest.Builder(QuoteWorker::class.java,30,TimeUnit.MINUTES)
            .setConstraints(constraint).build()
        WorkManager.getInstance(this).enqueue(workerRequest)
    }

    private fun initialize() {
        val quotesService = RetrofitHelper.getInstance().create(QuotesService::class.java)
        val database = QuoteDatabase.getDatabase(applicationContext  )
        quotesRepo = QuotesRepo(quotesService,database,applicationContext)
    }
}