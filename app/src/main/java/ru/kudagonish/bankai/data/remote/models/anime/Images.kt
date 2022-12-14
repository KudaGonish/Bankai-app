package ru.kudagonish.bankai.data.remote.models.anime

import com.google.gson.annotations.SerializedName

data class Images (
    @SerializedName("original")   val original: String,
    @SerializedName("preview")    val preview: String,
    @SerializedName("x96")        val x48: String,
    @SerializedName("x48")        val x96: String
)