package com.example.cactus.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cactus.model.Cactus

class DetailViewModel: ViewModel() {
    // สร้าง MutableLiveData สำหรับเก็บข้อมูล Cactus
    val selectedCactus: MutableLiveData<Cactus?> = MutableLiveData()

    // กำหนดข้อมูล Cactus ใน MutableLiveData
    fun setCactus(cactus: Cactus){
        selectedCactus.value = cactus
    }
}