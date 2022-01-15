package com.example.trevelplannerkelana.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubLocation(
    var name:String,
    var img:String,
    var code:String
): Parcelable
