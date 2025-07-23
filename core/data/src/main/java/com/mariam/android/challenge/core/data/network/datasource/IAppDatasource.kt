package com.mariam.android.challenge.core.data.network.datasource

import com.mariam.android.challenge.core.data.models.ItemAPIModel
import retrofit2.Response

interface IAppDatasource {
    suspend fun getItems(pageId: String): Response<ItemAPIModel>
}