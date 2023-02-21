package com.meghamlabs.musicwiki.data.model


data class Bio(
    val content: String,
    val links: Links,
    val published: String,
    val summary: String
)