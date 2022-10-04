package com.emmanull.ibstest.data.api

import com.emmanull.ibstest.data.api.model.ApiObj
import retrofit2.http.GET

interface ApiService {

    @GET("https://randomuser.me/api")
    suspend fun getResults(): ApiObj
}