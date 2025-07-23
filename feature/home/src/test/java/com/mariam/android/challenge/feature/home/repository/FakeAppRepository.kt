package com.mariam.android.challenge.feature.home.repository

import com.mariam.android.challenge.core.domain.models.ItemStateModel
import com.mariam.android.challenge.core.domain.repository.AppRepository

class FakeAppRepository : AppRepository {
    private val mockData = ItemStateModel.Page(
        title = "Page Title",
        items = listOf(
            ItemStateModel.Section(
                title = "Section A",
                items = listOf(
                    ItemStateModel.Text(title = "Hello World"),
                    ItemStateModel.Image(
                        title = "Image A",
                        src = "https://example.com/image.jpg",
                    )
                )
            ),
            ItemStateModel.Text(title = "Footer Text"),
        )
    )

    var cachedItems: ItemStateModel = mockData
    var networkResult: Result<ItemStateModel> = Result.success(mockData)

    override suspend fun getItemsFromDb(): ItemStateModel {
        return cachedItems
    }

    override suspend fun getItems(pageId: String): Result<ItemStateModel> {
        return networkResult
    }
}
