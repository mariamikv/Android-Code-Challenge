package com.mariam.android.challenge.core.data.network.service

import com.mariam.android.challenge.core.data.BaseResponse
import com.mariam.android.challenge.core.data.models.ItemAPIModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response

interface ApiService {
    @GET("/{page_id}")
    suspend fun getItems(
        @Path(value = "page_id") pageId: String,
    ): Response<BaseResponse<List<ItemAPIModel>>>
}