package com.mariam.android.challenge.core.domain.models

sealed class ItemStateModel(
    open val title: String,
    val type: ItemType,
) {
    data class Text(
        override val title: String,
    ) : ItemStateModel(
        title = title,
        type = ItemType.TEXT,
    )

    data class Image(
        override val title: String,
        val src: String,
    ) : ItemStateModel(
        title = title,
        type = ItemType.IMAGE,
    )

    data class Section(
        override val title: String,
        val items: List<ItemStateModel>,
    ) : ItemStateModel(
        title = title,
        type = ItemType.SECTION,
    )

    data class Page(
        override val title: String,
        val items: List<ItemStateModel>,
    ) : ItemStateModel(
        title = title,
        type = ItemType.PAGE,
    )
}