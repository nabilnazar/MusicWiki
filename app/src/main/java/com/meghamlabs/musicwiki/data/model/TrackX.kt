package com.meghamlabs.musicwiki.data.model


import com.google.gson.annotations.SerializedName
import com.meghamlabs.musicwiki.data.model.ArtistXXX
import com.meghamlabs.musicwiki.data.model.AttrXXXXXXX
import com.meghamlabs.musicwiki.data.model.StreamableX

data class TrackX(
    val artist: ArtistXXX,
    @SerializedName("@attr")
    val attr: AttrXXXXXXX,
    val duration: Int,
    val name: String,
    val streamable: StreamableX,
    val url: String
)