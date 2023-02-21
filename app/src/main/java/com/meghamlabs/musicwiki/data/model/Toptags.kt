package com.meghamlabs.musicwiki.data.model


import com.google.gson.annotations.SerializedName
import com.meghamlabs.musicwiki.data.model.Attr
import com.meghamlabs.musicwiki.data.model.Tag

data class Toptags(
    @SerializedName("@attr")
    val attr: Attr,
    val tag: List<Tag>
)