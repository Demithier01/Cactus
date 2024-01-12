package com.example.cactus.homePage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cactus.model.Cactus
import com.example.cactus.model.CactusGenerator
class HomepageViewModel : ViewModel() {
    // LiveData สำหรับเก็บรายการ Cactus
    val cactusList: MutableLiveData<List<Cactus>> = MutableLiveData()

    init {
        // กำหนดค่าเริ่มต้นให้กับ LiveData cactusList เพื่อสร้างlistรายการ Cactus
        cactusList.value = CactusGenerator.getItems()
    }
}
