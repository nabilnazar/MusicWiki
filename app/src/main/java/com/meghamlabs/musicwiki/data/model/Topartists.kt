package com.meghamlabs.musicwiki.data.model


import com.google.gson.annotations.SerializedName
import com.meghamlabs.musicwiki.data.model.ArtistX
import com.meghamlabs.musicwiki.data.model.AttrXXXX

data class Topartists(
    val artist: List<ArtistX>,
    @SerializedName("@attr")
    val attr: AttrXXXX
)