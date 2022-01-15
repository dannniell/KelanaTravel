package com.example.trevelplannerkelana.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    var name:String,
    var img:String
): Parcelable