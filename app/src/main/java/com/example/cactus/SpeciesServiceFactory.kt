package com.example.cactus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cactus.api.SpeciesService

class SpeciesServiceFactory(private val speciesService: SpeciesService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RetrofitViewModel(speciesService) as T
    }
}