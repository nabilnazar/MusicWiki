package com.meghamlabs.musicwiki.data.lastfmapi

import com.meghamlabs.musicwiki.BuildConfig
import com.meghamlabs.musicwiki.data.model.Genre
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApiService{

    @GET("/2.0")
    suspend fun getTopTags(

        @Query("method")
        method:String="tag.getTopTags",

        @Query("api_key")
        api_key: String = BuildConfig.API_KEY,

        @Query("format")
        format:String="json"

    ): Response<Genre>


}