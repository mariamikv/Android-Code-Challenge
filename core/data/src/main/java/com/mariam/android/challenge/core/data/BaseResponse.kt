package com.mariam.android.challenge.core.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BaseResponse<Data: Any>(
    val data: Data?,
    val error: String? = null
)