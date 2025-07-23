package com.mariam.android.challenge.core.data.repository

import com.mariam.android.challenge.core.data.mapper.toItemStateModel
import com.mariam.android.challenge.core.data.network.datasource.IAppDatasource
import com.mariam.android.challenge.core.domain.models.ItemStateModel
import com.mariam.android.challenge.core.domain.repository.AppRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val datasource: IAppDatasource,
) : AppRepository {
    override suspend fun getItems(pageId: String): Result<ItemStateModel> {
        return try {
            val response = datasource.getItems(pageId = pageId)

            val body = response.body()
            println(body)

            if (response.isSuccessful && body != null) {
                val items = body.toItemStateModel()
                Result.success(items)
            } else {
                Result.failure(Exception("Unexpected response: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
