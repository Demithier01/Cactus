package com.example.cactus.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cactus.ui.retrofit.RetrofitViewModel

class ServiceFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
            return RetrofitViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
