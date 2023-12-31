package com.example.cactus.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cactus(
    val name: String,
    val description: String,
    val image: Int,
    val link : String
): Parcelable
