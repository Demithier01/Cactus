package com.example.cactus

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cactus.api.SpeciesService
import com.example.cactus.model.SpeciesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitViewModel(private val speciesService: SpeciesService) : ViewModel() {

    private val _speciesList = MutableLiveData<List<SpeciesItem>>()
    val speciesList: LiveData<List<SpeciesItem>?> = _speciesList

    //GET Data
    fun fetchData() {
        val getCall = speciesService.getSpecies()
        getCall.enqueue(object : Callback<Map<String, SpeciesItem>> {
            override fun onResponse(call: Call<Map<String, SpeciesItem>>, response: Response<Map<String, SpeciesItem>>) {
                if (response.isSuccessful) {
                    val list = ArrayList<SpeciesItem>()
                    val species = response.body()
                    if (species != null) {
                        for ((_, item) in species.entries) {
                            list.add(item)
                        }
                    }
                    _speciesList.postValue(list)
                } else {
                    Log.e("RetrofitViewModel", "Error getting : ${response.code()}")

                }
            }
            override fun onFailure(call: Call<Map<String, SpeciesItem>>, t: Throwable) {
                handleFailure(t)
            }
        })
    }
    //POST Data
    fun createData(speciesItem: SpeciesItem) {
        val postCall = speciesService.createSpecies(speciesItem)
        postCall.enqueue(object : Callback<SpeciesItem> {
            override fun onResponse(call: Call<SpeciesItem>, response: Response<SpeciesItem>) {
                if (response.isSuccessful) {
                    val responseItem = response.body()
                    if (responseItem != null) {
                        Log.e("RetrofitViewModel", "Create data Success : ${response.code()}")
                        fetchData()
                    }
                } else {
                    Log.e("RetrofitViewModel", "Error Create data : ${response.code()}")
                }
            }
            override fun onFailure(call: Call<SpeciesItem>, t: Throwable) {
                handleFailure(t)
            }
        })
    }
//    fun updateData(speciesItem: SpeciesItem) {
//    val updateCall = speciesService.updateSpecies("", speciesItem)
//    updateCall.enqueue(object : Callback<SpeciesItem> {
//        override fun onResponse(call: Call<SpeciesItem>, response: Response<SpeciesItem>) {
//            if (response.isSuccessful) {
//                val updatedSpecies = response.body()
//                if (updatedSpecies != null) {
//                    Log.e("RetrofitViewModel", "Update Success!!: ${response.code()}")
//                }
//            } else {
//                Log.e("RetrofitViewModel", "Error updating : ${response.code()}")
//            }
//        }
//
//        override fun onFailure(call: Call<SpeciesItem>, t: Throwable) {
//            handleFailure(t)
//        }
//    })
//}


    //DELETE Data
    private fun handleFailure(t: Throwable) {
        val failureMessage = "Failure: ${t.message}"
        Log.e("RetrofitViewModel", failureMessage)
    }
}


//DELETE Data
//fun deleteData(id: String) {
//    val deleteCall = speciesService.deleteSpecies(id)
//    deleteCall.enqueue(object : Callback<SpeciesItem> {
//        override fun onResponse(call: Call<SpeciesItem>, response: Response<SpeciesItem>) {
//            if (response.isSuccessful) {
//                val deleteItem = response.body()
//                if (deleteItem != null) {
//                    Log.e("RetrofitViewModel", "Delete Success!!: ${response.code()}")
//                }
//            } else {
//                Log.e("RetrofitViewModel", "Error deleting : ${response.code()}")
//            }
//        }
//
//        override fun onFailure(call: Call<SpeciesItem>, t: Throwable) {
//            handleFailure(t)
//        }
//    })
//}

//PATCH Data
//fun updateData(speciesItem: SpeciesItem) {
//    val updateCall = speciesService.updateSpecies("", speciesItem)
//    updateCall.enqueue(object : Callback<SpeciesItem> {
//        override fun onResponse(call: Call<SpeciesItem>, response: Response<SpeciesItem>) {
//            if (response.isSuccessful) {
//                val updatedSpecies = response.body()
//                if (updatedSpecies != null) {
//                    Log.e("RetrofitViewModel", "Update Success!!: ${response.code()}")
//                }
//            } else {
//                Log.e("RetrofitViewModel", "Error updating : ${response.code()}")
//            }
//        }
//
//        override fun onFailure(call: Call<SpeciesItem>, t: Throwable) {
//            handleFailure(t)
//        }
//    })
//}