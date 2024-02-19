package com.example.cactus.ui.retrofit

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
    val speciesList: LiveData<List<SpeciesItem>> = _speciesList

    private val _updatedSpecies = MutableLiveData<SpeciesItem?>()
    val updatedSpecies: LiveData<SpeciesItem?> = _updatedSpecies

    init {
        fetchData()
    }

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
    //POST
    fun createItem(speciesItem: SpeciesItem) {
        val id = speciesItem.id
        if (id != null) {
        val postCall = speciesService.createSpecies(id,speciesItem)
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
    }}
    //UPDATE
    private fun updateItem(updatedSpecies: SpeciesItem) {
        val updateCall = speciesService.updateSpecies(updatedSpecies.id, updatedSpecies)
        updateCall.enqueue(object : Callback<SpeciesItem> {
            override fun onResponse(call: Call<SpeciesItem>, response: Response<SpeciesItem>) {
                if (response.isSuccessful) {
                    val updatedSpecies = response.body()
                    _updatedSpecies.postValue(updatedSpecies)
                } else {
                    _updatedSpecies.postValue(null)
                    Log.e("RetrofitViewModel", "Error updating : ${response.code()}")
                }
            }
            override fun onFailure(call: Call<SpeciesItem>, t: Throwable) {
            _updatedSpecies.postValue(null)
            handleFailure(t)
            }
        })
    }
    fun updateDataById(id: String?, speciesItem: SpeciesItem) {
        val updatedSpecies = speciesItem.copy(id = id)
        updateItem(updatedSpecies)
    }
    //DELETE
    fun deleteItem(speciesItem: SpeciesItem, onResult: (Boolean) -> Unit) {
        val id = speciesItem.id
        if (id != null) {
            val deleteCall = speciesService.deleteSpecies(id)
            deleteCall.enqueue(object : Callback<SpeciesItem> {
                override fun onResponse(call: Call<SpeciesItem>, response: Response<SpeciesItem>) {
                    if (response.isSuccessful) {
                        onResult.invoke(true)
                        fetchData()
                    } else {
                        onResult.invoke(false)
                        Log.e("RetrofitViewModel", "Error deleting : ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<SpeciesItem>, t: Throwable) {
                    onResult.invoke(false)
                    handleFailure(t)
                }
            })
        }
    }

    private fun handleFailure(t: Throwable) {
        val failureMessage = "Failure: ${t.message}"
        Log.e("RetrofitViewModel", failureMessage)
    }
}
