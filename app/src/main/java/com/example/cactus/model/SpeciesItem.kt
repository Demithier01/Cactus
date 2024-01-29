package com.example.cactus.model

import com.google.gson.annotations.SerializedName

data class SpeciesItem (
    @SerializedName("name") val name: String? = null,
    @SerializedName("title") val title : String? = null,
    @SerializedName("image_url") val imageUrl : String? = null
)

class Species: ArrayList<SpeciesItem>() {
}
