package com.meghamlabs.musicwiki.data.model


import com.google.gson.annotations.SerializedName
import com.meghamlabs.musicwiki.data.model.AlbumXX
import com.meghamlabs.musicwiki.data.model.AttrXXXXXXXXXX

data class Topalbums(
    val album: List<AlbumXX>,
    @SerializedName("@attr")
    val attr: AttrXXXXXXXXXX
)