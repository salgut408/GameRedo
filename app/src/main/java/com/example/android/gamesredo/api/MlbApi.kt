package com.example.android.gamesredo.api

import com.example.android.gamesredo.MlbResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MlbApi {
    @GET("v1/schedule")
    suspend fun getGames(
        @Query("sportId")
        sportId: Int = 1
    ): Response<MlbResponse>
}