package com.kuit.ourmenu.data.model.base

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

class OurMenuApiFailureException(
    val status: Int? = null,
    private val code: String? = null,
    override val message: String? = null
) : Exception(
    "OurMenu API failure: status = $status, code = $code, message = $message"
)