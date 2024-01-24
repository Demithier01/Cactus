package com.example.cactus.restApi.model

import com.google.gson.annotations.SerializedName

data class SpeciesItem (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("title") val title : String
)
