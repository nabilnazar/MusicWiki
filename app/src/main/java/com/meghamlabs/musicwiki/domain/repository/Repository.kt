package com.meghamlabs.musicwiki.domain.repository

import com.meghamlabs.musicwiki.data.RetrofitInstance
import com.meghamlabs.musicwiki.data.model.Genre
import retrofit2.Response

class Repository {

    suspend fun getTopTags(): Response<Genre>{
        return RetrofitInstance.api.getTopTags()
    }


}
