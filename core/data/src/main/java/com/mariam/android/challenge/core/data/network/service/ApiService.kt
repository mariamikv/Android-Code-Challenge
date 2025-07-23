package com.mariam.android.challenge.core.data.network.service

import com.mariam.android.challenge.core.data.models.ItemAPIModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/v1/{page_id}")
    suspend fun getItems(
        @Path(value = "page_id") pageId: String,
    ): Response<ItemAPIModel>
}