package com.example.trevelplannerkelana.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailDoc(
    var judul: String,
    var loc: String,
    var des: String
): Parcelable
