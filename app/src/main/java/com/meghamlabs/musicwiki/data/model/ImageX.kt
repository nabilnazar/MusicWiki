package com.meghamlabs.musicwiki.data.model


import com.google.gson.annotations.SerializedName

data class ImageX(
    val size: String,
    @SerializedName("#text")
    val text: String
)