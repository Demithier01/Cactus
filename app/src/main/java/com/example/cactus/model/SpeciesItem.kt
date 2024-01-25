package com.example.cactus.model

import com.google.gson.annotations.SerializedName

data class SpeciesItem (
    @SerializedName("name") val name: String,
    @SerializedName("title") val title : String
)

class Species: ArrayList<SpeciesItem>() {
}