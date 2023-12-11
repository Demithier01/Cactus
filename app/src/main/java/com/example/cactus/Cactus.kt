package com.example.cactus

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cactus( val name: String,
                   val description: String,
                   val image: Int ): Parcelable
