package ru.stplab.gityoungclient2.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GitUser(
    @Expose val id: String,
    @Expose val login: String,
    @Expose val avatarUrl: String?,
    @Expose val reposUrl: String
) : Parcelable