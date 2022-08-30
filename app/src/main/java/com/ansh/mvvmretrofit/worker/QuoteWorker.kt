package com.ansh.mvvmretrofit.worker

import android.content.Context
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ansh.mvvmretrofit.QuoteApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteWorker(private val context: Context,params: WorkerParameters): Worker(context,params) {
    override fun doWork(): Result {
        val repository = (context as QuoteApplication).quotesRepo
        CoroutineScope(Dispatchers.IO).launch {
            repository.getQuotesBackground()
        }
        return Result.success()
    }
}