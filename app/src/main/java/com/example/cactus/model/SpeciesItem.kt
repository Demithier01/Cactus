package com.example.cactus.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpeciesItem (
    @SerializedName("name") val name: String? = null,
    @SerializedName("title") val title : String? = null,
    @SerializedName("image_url") val imageUrl : String? = null
): Parcelable

class Species: ArrayList<SpeciesItem>() {
}
