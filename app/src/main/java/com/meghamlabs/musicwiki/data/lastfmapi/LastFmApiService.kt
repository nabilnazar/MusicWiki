package com.meghamlabs.musicwiki.data.lastfmapi

import com.meghamlabs.musicwiki.BuildConfig
import com.meghamlabs.musicwiki.data.model.*
import genreAlbums
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmApiService{

    @GET("/2.0")
    suspend fun getTopTags(
        @Query("method") method:String="tag.getTopTags",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): Response<Genre>

    @GET("/2.0")
    suspend fun getTagInfo(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getInfo",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): Response<genreInfo>

    @GET("/2.0")
    suspend fun getTopAlbums(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopAlbums",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): Response<genreAlbums>

    @GET("/2.0")
    suspend fun getTopArtists(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopArtists",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): Response<genreArtists>

    @GET("/2.0")
    suspend fun getTopTracks(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopTracks",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ) : Response<genreTracks>

    @GET("/2.0")
    suspend fun getAlbumInfo(
        @Query("artist")artist:String,
        @Query("album")album:String,
        @Query("method") method:String="album.getInfo",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ) : Response<albumInfo>

    @GET("/2.0")
    suspend fun getArtistInfo(
        @Query("artist")artist:String,
        @Query("method") method:String="artist.getInfo",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ) : Response<artistInfo>

    @GET("/2.0")
    suspend fun getArtistTopAlbums(
        @Query("artist") artist:String,
        @Query("method") method:String="artist.getTopAlbums",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): Response<artistTopAlbums>

    @GET("/2.0")
    suspend fun getArtistTopTracks(
        @Query("artist") artist:String,
        @Query("method") method:String="artist.getTopTracks",
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ) : Response<artistTopTracks>

}
