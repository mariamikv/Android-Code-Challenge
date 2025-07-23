package com.mariam.android.challenge.core.data.repository

import com.mariam.android.challenge.core.data.database.ItemDao
import com.mariam.android.challenge.core.data.database.ItemEntity
import com.mariam.android.challenge.core.data.mapper.mapToItemStateModelTree
import com.mariam.android.challenge.core.data.mapper.toItemStateModel
import com.mariam.android.challenge.core.data.models.ItemAPIModel
import com.mariam.android.challenge.core.data.network.datasource.IAppDatasource
import com.mariam.android.challenge.core.domain.models.ItemStateModel
import com.mariam.android.challenge.core.domain.repository.AppRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val datasource: IAppDatasource,
    private val dao: ItemDao,
) : AppRepository {
    override suspend fun getItems(pageId: String): Result<ItemStateModel> {
        return try {
            val response = datasource.getItems(pageId = pageId)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                val items = body.toItemStateModel()

                dao.clearAll()
                insertItemRecursively(body, parentId = null, dao = dao)

                Result.success(items)
            } else {
                Result.failure(Exception("Unexpected response: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getItemsFromDb(): ItemStateModel? {
        val allItems = dao.getAllItems()
        val rootItems = mapToItemStateModelTree(allItems)

        return rootItems.firstOrNull { it is ItemStateModel.Page }
    }

    suspend fun insertItemRecursively(item: ItemAPIModel, parentId: Long? = null, dao: ItemDao) {
        val entity = when (item) {
            is ItemAPIModel.Text -> ItemEntity(
                title = item.title,
                type = "text",
                parentId = parentId,
            )

            is ItemAPIModel.Image -> ItemEntity(
                title = item.title,
                type = "image",
                imageUrl = item.src,
                parentId = parentId,
            )

            is ItemAPIModel.Section -> ItemEntity(
                title = item.title,
                type = "section",
                parentId = parentId,
            )

            is ItemAPIModel.Page -> ItemEntity(
                title = item.title,
                type = "page",
                parentId = parentId,
            )
        }

        val newId = dao.insertSingle(entity)

        val children = when (item) {
            is ItemAPIModel.Section -> item.items
            is ItemAPIModel.Page -> item.items
            else -> emptyList()
        }

        children.forEach {
            insertItemRecursively(it, newId, dao)
        }
    }
}
