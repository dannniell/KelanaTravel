package com.example.trevelplannerkelana.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plan(
    var img: String,
    var name: String,
    var code: String,
    var collectionData: String
): Parcelable
