package com.mariam.android.challenge.core.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
enum class ItemAPIType {
    @Json(name = "text")
    TEXT,

    @Json(name = "image")
    IMAGE,

    @Json(name = "section")
    SECTION,

    @Json(name = "page")
    PAGE,
}