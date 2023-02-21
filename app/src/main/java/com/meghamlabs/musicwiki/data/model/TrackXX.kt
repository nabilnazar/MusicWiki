package com.meghamlabs.musicwiki.data.model


import com.google.gson.annotations.SerializedName
import com.meghamlabs.musicwiki.data.model.ArtistXXXXXX
import com.meghamlabs.musicwiki.data.model.AttrXXXXXXXXX
import com.meghamlabs.musicwiki.data.model.ImageXXXXX

data class TrackXX(
    val artist: ArtistXXXXXX,
    @SerializedName("@attr")
    val attr: AttrXXXXXXXXX,
    val image: List<ImageXXXXX>,
    val listeners: String,
    val mbid: String,
    val name: String,
    val playcount: String,
    val streamable: String,
    val url: String
)