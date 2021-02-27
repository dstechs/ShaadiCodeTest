package com.dinesh.shaadi.data.remote

import com.dinesh.shaadi.data.entities.BaseResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface MatchesService {
    @GET("api/")
    suspend fun getMatches(@Query("results") limit: Int): Response<BaseResponseModel>
}