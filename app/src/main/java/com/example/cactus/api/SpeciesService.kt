package com.example.cactus.api

import com.example.cactus.model.SpeciesItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface SpeciesService {
    @GET("/backup/cactus/.json")
    fun getSpecies(): Call<Map<String, SpeciesItem>>

    @PATCH("/backup/cactus/{id}.json")
    fun updateSpecies(@Path("id") id: String, @Body speciesItem: SpeciesItem): Call<SpeciesItem>

    @POST("/backup/cactus/.json")
    fun createSpecies(@Body speciesItem: SpeciesItem): Call<SpeciesItem>

    @DELETE("/backup/cactus/{id}.json")
    fun deleteSpecies(@Path("id")id: String): Call<SpeciesItem>
}

