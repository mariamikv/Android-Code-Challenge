package com.mariam.android.challenge.core.data.models

import com.squareup.moshi.JsonClass
import dev.zacsweers.moshix.sealed.annotations.TypeLabel

@JsonClass(generateAdapter = true, generator = "sealed:type")
sealed class ItemAPIModel(
    open val title: String,
    val type: ItemAPIType,
) {
    @TypeLabel("text")
    @JsonClass(generateAdapter = true)
    data class Text(
        override val title: String,
    ) : ItemAPIModel(
        title = title,
        type = ItemAPIType.TEXT,
    )

    @TypeLabel("image")
    @JsonClass(generateAdapter = true)
    data class Image(
        override val title: String,
        val src: String,
    ) : ItemAPIModel(
        title = title,
        type = ItemAPIType.IMAGE,
    )

    @TypeLabel("section")
    @JsonClass(generateAdapter = true)
    data class Section(
        override val title: String,
        val items: List<ItemAPIModel>,
    ) : ItemAPIModel(
        title = title,
        type = ItemAPIType.SECTION,
    )

    @TypeLabel("page")
    @JsonClass(generateAdapter = true)
    data class Page(
        override val title: String,
        val items: List<ItemAPIModel>,
    ) : ItemAPIModel(
        title = title,
        type = ItemAPIType.PAGE,
    )
}

