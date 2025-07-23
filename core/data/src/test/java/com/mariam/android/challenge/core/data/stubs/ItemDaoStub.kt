package com.mariam.android.challenge.core.data.stubs

import com.mariam.android.challenge.core.data.database.ItemDao
import com.mariam.android.challenge.core.data.database.ItemEntity

class ItemDaoStub : ItemDao {

    private val items = mutableListOf<ItemEntity>()
    private var nextId = 1L

    override suspend fun insertSingle(item: ItemEntity): Long {
        val newItem = item.copy(id = nextId++)
        items.add(newItem)
        return newItem.id
    }

    override suspend fun getAllItems(): List<ItemEntity> {
        return items.toList()
    }

    override suspend fun clearAll() {
        items.clear()
        nextId = 1L
    }
}
