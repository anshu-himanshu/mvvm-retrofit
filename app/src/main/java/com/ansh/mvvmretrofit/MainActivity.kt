package com.ansh.mvvmretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ansh.mvvmretrofit.api.QuotesService
import com.ansh.mvvmretrofit.api.RetrofitHelper
import com.ansh.mvvmretrofit.repo.QuotesRepo
import com.ansh.mvvmretrofit.vm.MainViewModel
import com.ansh.mvvmretrofit.vm.MainViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = (application as QuoteApplication).quotesRepo




        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repo))[MainViewModel::class.java]

        mainViewModel.quotes.observe(this, Observer{
            Toast.makeText(this@MainActivity, it.results.size.toString( ),Toast.LENGTH_SHORT).show()

        })

    }
}