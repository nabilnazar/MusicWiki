package com.meghamlabs.musicwiki.data.model


import com.google.gson.annotations.SerializedName
import com.meghamlabs.musicwiki.data.model.AttrXXXXX
import com.meghamlabs.musicwiki.data.model.Track

data class Tracks(
    @SerializedName("@attr")
    val attr: AttrXXXXX,
    val track: List<Track>
)