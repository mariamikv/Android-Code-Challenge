package com.mariam.android.challenge.core.data.repository

import com.mariam.android.challenge.core.data.ResponseMode
import com.mariam.android.challenge.core.data.stubs.AppDataSourceStub
import com.mariam.android.challenge.core.data.stubs.ItemDaoStub
import com.mariam.android.challenge.core.domain.models.ItemStateModel
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AppRepositoryTest {
    private val datasource = AppDataSourceStub()
    private val dao = ItemDaoStub()

    private val repository = AppRepositoryImpl(
        datasource = datasource,
        dao = dao,
    )

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

    @Before
    fun setup() {
        datasource.setResultMode(ResponseMode.ReturnMode.SUCCESS)
    }

    @Test
    fun getItemsStatusSuccess() = runTest {
        val response = repository.getItems(
            pageId = "page_id",
        )

        response.isSuccess.shouldBeTrue()

        val data = response.getOrNull()

        data shouldNotBe null
    }

    @Test
    fun getItemsStatusError() = runTest {
        datasource.setResultMode(ResponseMode.ReturnMode.ERROR)

        val response = repository.getItems(
            pageId = "page_id",
        )

        response.isFailure.shouldBeTrue()

        val data = response.getOrNull()

        data shouldBe null
    }

    @Test
    fun getItemsFromDbReturnsCorrectPage() = runTest {
        repository.getItems("page_id")

        val result = repository.getItemsFromDb()

        result shouldNotBe null
        result shouldBe mockData
    }
}