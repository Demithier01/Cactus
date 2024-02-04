package com.example.cactus.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private const val BASE_URL = "https://cactus-102-default-rtdb.firebaseio.com/cactus/.json/"
        private val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun getRetrofitInstance(): Retrofit{
            return retrofit
        }
    }
}