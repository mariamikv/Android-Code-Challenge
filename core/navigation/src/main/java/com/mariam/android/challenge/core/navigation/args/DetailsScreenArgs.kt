package com.mariam.android.challenge.core.navigation.args

import kotlinx.serialization.Serializable

@Serializable
data class DetailsScreenArgs(
    val title: String,
    val src: String,
)