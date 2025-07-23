package com.mariam.android.challenge.core.domain.repository

import com.mariam.android.challenge.core.domain.models.ItemStateModel

interface AppRepository {
    suspend fun getItems(pageId: String): Result<ItemStateModel>
}