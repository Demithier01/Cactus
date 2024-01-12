package com.example.cactus.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cactus.model.Cactus
import com.example.cactus.model.CactusGenerator

class HomepageViewModel: ViewModel() {
    val cactusList: List<Cactus> = CactusGenerator.getItems()
}