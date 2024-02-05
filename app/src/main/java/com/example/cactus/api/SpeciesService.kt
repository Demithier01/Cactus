package com.example.cactus.api

import com.example.cactus.model.SpeciesItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SpeciesService {
    @GET("/cactus.json")
    fun getSpecies(): Call<Map<String,SpeciesItem>>
    @PUT("/cactus/{id}.json")
    fun updateSpecies(@Path("id") id: String?, @Body speciesItem: SpeciesItem): Call<SpeciesItem>

    @PUT("/cactus/{id}.json")
    fun createSpecies(@Path("id") id : String,@Body speciesItem: SpeciesItem): Call<SpeciesItem>

    @DELETE("/cactus/{id}.json")
    fun deleteSpecies(@Path("id")id: String): Call<SpeciesItem>
}

