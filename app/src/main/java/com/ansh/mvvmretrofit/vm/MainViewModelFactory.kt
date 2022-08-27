package com.ansh.mvvmretrofit.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ansh.mvvmretrofit.repo.QuotesRepo

class MainViewModelFactory(private val repository : QuotesRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }


}