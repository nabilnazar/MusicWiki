package com.meghamlabs.musicwiki.data.model


import com.google.gson.annotations.SerializedName
import com.meghamlabs.musicwiki.data.model.AlbumXXX

data class TopalbumsX(
    val album: List<AlbumXXX>,
    @SerializedName("@attr")
    val attr: AttrXXXXXXXXXXX
)