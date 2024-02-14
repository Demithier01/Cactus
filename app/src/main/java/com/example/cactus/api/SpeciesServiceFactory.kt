package com.example.cactus.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cactus.ui.retrofit.RetrofitViewModel

class SpeciesServiceFactory(private val speciesService: SpeciesService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return RetrofitViewModel(speciesService) as T
//    }
        if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
            return RetrofitViewModel(speciesService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
