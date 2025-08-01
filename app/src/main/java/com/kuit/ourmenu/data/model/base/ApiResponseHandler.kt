package com.kuit.ourmenu.data.model.base

import android.R.id.message

fun <T> BaseResponse<T>.handleBaseResponse(): Result<T?> =
    if (isSuccess) {
        Result.success(response)
    } else {
        Result.failure(
            OurMenuApiFailureException(
                errorResponse?.status,
                errorResponse?.code,
                errorResponse?.message
            )
        )
    }

data class OurMenuApiFailureException(
    val status: Int? = null,
    val code: String? = null,
    override val message: String? = null
) : Throwable()