package com.mariam.android.challenge.core.data.mapper

import com.mariam.android.challenge.core.data.database.ItemEntity
import com.mariam.android.challenge.core.data.models.ItemAPIModel
import com.mariam.android.challenge.core.domain.models.ItemStateModel

fun ItemAPIModel.toItemStateModel(): ItemStateModel {
    return when (this) {
        is ItemAPIModel.Text -> ItemStateModel.Text(title = this.title)
        is ItemAPIModel.Image -> ItemStateModel.Image(
            title = this.title,
            src = this.src,
        )

        is ItemAPIModel.Section -> ItemStateModel.Section(
            title = this.title,
            items = this.items.map { it.toItemStateModel() },
        )

        is ItemAPIModel.Page -> ItemStateModel.Page(
            title = this.title,
            items = this.items.map { it.toItemStateModel() },
        )
    }
}

fun ItemAPIModel.toEntityList(parentId: Long? = null): List<ItemEntity> {
    val result = mutableListOf<ItemEntity>()
    val currentItem = when (this) {
        is ItemAPIModel.Text -> ItemEntity(
            title = title,
            type = type.name.lowercase(),
            parentId = parentId
        )

        is ItemAPIModel.Image -> ItemEntity(
            title = title,
            type = type.name.lowercase(),
            imageUrl = src,
            parentId = parentId
        )

        is ItemAPIModel.Section, is ItemAPIModel.Page -> ItemEntity(
            title = title,
            type = type.name.lowercase(),
            parentId = parentId
        )
    }

    result.add(currentItem)

    val children = when (this) {
        is ItemAPIModel.Section -> items
        is ItemAPIModel.Page -> items
        else -> emptyList()
    }

    val parentEntityId = 0L
    children.forEach { child ->
        result.addAll(child.toEntityList(parentId = parentEntityId))
    }

    return result
}

fun mapToItemStateModelTree(
    items: List<ItemEntity>,
    parentId: Long? = null,
): List<ItemStateModel> {
    return items
        .filter { it.parentId == parentId }
        .map { entity ->
            val children = mapToItemStateModelTree(items, entity.id)

            when (entity.type) {
                "text" -> ItemStateModel.Text(
                    title = entity.title,
                )

                "image" -> ItemStateModel.Image(
                    title = entity.title,
                    src = entity.imageUrl.orEmpty(),
                )

                "section" -> ItemStateModel.Section(
                    title = entity.title,
                    items = children,
                )

                "page" -> ItemStateModel.Page(
                    title = entity.title,
                    items = children,
                )

                else -> ItemStateModel.Text(
                    title = entity.title,
                )
            }
        }
}