package com.mariam.android.challenge.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSingle(item: ItemEntity): Long

    @Query("SELECT * FROM items")
    suspend fun getAllItems(): List<ItemEntity>

    @Query("DELETE FROM items")
    suspend fun clearAll()
}
