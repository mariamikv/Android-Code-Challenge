package com.mariam.android.challenge.core.data

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

abstract class ResponseMode {
    protected var mode: ReturnMode = ReturnMode.SUCCESS

    fun setResultMode(mode: ReturnMode) {
        this.mode = mode
    }

    enum class ReturnMode {
        SUCCESS,
        ERROR,
    }
}

fun <T> genericDataSourceError() = Response.error<T>(
    404,
    "{\"key\":[\"somestuff\"]}".toResponseBody(contentType = "application/json".toMediaTypeOrNull()),
)