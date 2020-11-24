package ru.stplab.gityoungclient2.mvp.model.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitUser (val login: String) : Parcelable