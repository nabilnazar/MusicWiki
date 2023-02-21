package com.meghamlabs.musicwiki.data.model


import com.google.gson.annotations.SerializedName
import com.meghamlabs.musicwiki.data.model.ArtistXX
import com.meghamlabs.musicwiki.data.model.AttrXXXXXX
import com.meghamlabs.musicwiki.data.model.ImageXX
import com.meghamlabs.musicwiki.data.model.Streamable

data class Track(
    val artist: ArtistXX,
    @SerializedName("@attr")
    val attr: AttrXXXXXX,
    val duration: String,
    val image: List<ImageXX>,
    val mbid: String,
    val name: String,
    val streamable: Streamable,
    val url: String
)