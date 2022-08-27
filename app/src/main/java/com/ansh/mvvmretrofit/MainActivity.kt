package com.ansh.mvvmretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ansh.mvvmretrofit.api.QuotesService
import com.ansh.mvvmretrofit.api.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesApi = RetrofitHelper.getInstance().create(QuotesService::class.java)
        GlobalScope.launch{
            val result =  quotesApi.getQuotes(1)
            if (result != null){
                 val quoteList = result.body()
                quoteList?.results?.forEach{
                    Log.d("ABCD",it.content)
                }
            }
        }
    }
}