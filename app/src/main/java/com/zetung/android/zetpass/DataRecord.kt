package com.zetung.android.zetpass

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataRecord(var app: String, var login: String, var pass: String):Parcelable
