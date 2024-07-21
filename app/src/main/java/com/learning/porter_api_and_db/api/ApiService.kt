package com.learning.porter_api_and_db.api

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getData():List<DataModel>
}