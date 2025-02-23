package com.kuit.ourmenu.data.model.base

fun <T> BaseResponse<T>.handleBaseResponse(): Result<T> =
    if (isSuccess) {
        Result.success(response)
    } else {
        Result.failure(OurMenuApiFailureException(
            errorResponse.status,
            errorResponse.code,
            errorResponse.message
        ))
    }

class OurMenuApiFailureException(
    status: Int,
    code: String,
    message: String
) : Exception(
    "OurMenu API failure: status = $status, code = $code, message = $message"
)