package com.redpepper.shikiapp.data.remote.models

import com.google.gson.annotations.SerializedName

data class AnimeEntityItem(
    @SerializedName("id")             val id: Int,
    @SerializedName("name")           val engName: String,
    @SerializedName("russian")        val rusName: String,
    @SerializedName("image")          val image: Images,
    @SerializedName("url")            val url: String,
    @SerializedName("kind")           val kind: String,
    @SerializedName("score")          val ratingScore: String,
    @SerializedName("status")         val status: String,
    @SerializedName("episodes")       val episodes: Int,
    @SerializedName("episodes_aired") val episodes_aired: Int,
    @SerializedName("aired_on")       val animeAiredOn: String,
    @SerializedName("released_on")    val animeReleasedOn: String
)
