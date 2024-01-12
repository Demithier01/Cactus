package com.example.cactus.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cactus.model.Cactus

class DetailViewModel: ViewModel() {
    var selectedCactus: Cactus? = null

    fun setCactus(cactus: Cactus){
        selectedCactus = cactus
    }
}