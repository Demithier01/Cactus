package com.example.cactus

import com.example.cactus.model.ToDo
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val BASE_URl ="https://mocki.io/"
interface ApiService {
    @GET("v1/ca6656d3-3bd2-4500-a949-25932772e574")
     fun getMessage(): Call<ToDo>

    companion object{
        operator fun invoke(): ApiService{
            return Retrofit.Builder()
                .baseUrl(BASE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}