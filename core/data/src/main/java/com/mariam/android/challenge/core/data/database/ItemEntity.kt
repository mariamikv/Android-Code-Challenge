package com.mariam.android.challenge.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val type: String,
    val imageUrl: String? = null,
    val parentId: Long? = null
)
