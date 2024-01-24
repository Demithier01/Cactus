package com.example.cactus.restApi

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private const val BASE_URL = "https://cactus-101-default-rtdb.firebaseio.com/cactus/.json/"
        private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getRetrofitInstance(): Retrofit{
            return retrofit
        }
    }
}