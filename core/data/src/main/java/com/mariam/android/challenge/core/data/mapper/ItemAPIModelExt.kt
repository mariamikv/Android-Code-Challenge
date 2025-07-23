package com.mariam.android.challenge.core.data.mapper

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
