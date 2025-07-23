package com.mariam.android.challenge.core.data.network.datasource

import com.mariam.android.challenge.core.data.models.ItemAPIModel
import com.mariam.android.challenge.core.data.network.service.ApiService
import retrofit2.Response
import javax.inject.Inject

class AppDatasourceImpl @Inject constructor(
    private val service: ApiService,
): IAppDatasource {
    override suspend fun getItems(pageId: String): Response<ItemAPIModel> {
        return service.getItems(pageId = pageId)
    }
}