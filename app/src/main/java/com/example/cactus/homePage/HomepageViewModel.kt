package com.example.cactus.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cactus.ApiService
import com.example.cactus.model.Cactus
import com.example.cactus.model.CactusGenerator
import com.example.cactus.model.ToDo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomepageViewModel : ViewModel() {
    // LiveData สำหรับเก็บรายการ Cactus
    val cactusList: MutableLiveData<List<Cactus>> = MutableLiveData()

    init {
        // กำหนดค่าเริ่มต้นให้กับ LiveData cactusList เพื่อสร้างlistรายการ Cactus
        cactusList.value = CactusGenerator.getItems()
    }
}



