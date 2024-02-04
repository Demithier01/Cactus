package com.example.cactus.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpeciesItem(
    val id: String? = null,
    val name: String? = null,
    val title: String? = null,
    val image: String? = null
): Parcelable

class Species: ArrayList<SpeciesItem>() {
}
