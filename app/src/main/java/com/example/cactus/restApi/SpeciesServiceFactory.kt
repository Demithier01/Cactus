package com.example.cactus.restApi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SpeciesServiceFactory(private val speciesService: SpeciesService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RetrofitViewModel(speciesService) as T
    }
}