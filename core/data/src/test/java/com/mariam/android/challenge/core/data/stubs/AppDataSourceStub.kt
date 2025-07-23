package com.mariam.android.challenge.core.data.stubs

import com.mariam.android.challenge.core.data.ResponseMode
import com.mariam.android.challenge.core.data.genericDataSourceError
import com.mariam.android.challenge.core.data.models.ItemAPIModel
import com.mariam.android.challenge.core.data.network.datasource.IAppDatasource
import retrofit2.Response

class AppDataSourceStub : ResponseMode(), IAppDatasource {
    private val mockData = ItemAPIModel.Page(
        title = "Page Title",
        items = listOf(
            ItemAPIModel.Section(
                title = "Section A",
                items = listOf(
                    ItemAPIModel.Text(title = "Hello World"),
                    ItemAPIModel.Image(
                        title = "Image A",
                        src = "https://example.com/image.jpg",
                    ),
                )
            ),
            ItemAPIModel.Text(title = "Footer Text"),
        )
    )

    override suspend fun getItems(pageId: String): Response<ItemAPIModel> {
        if (mode == ReturnMode.ERROR) {
            return genericDataSourceError()
        }

        return Response.success(mockData)
    }
}