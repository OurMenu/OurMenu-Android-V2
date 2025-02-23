package com.kuit.ourmenu.data.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("isSuccess") val isSuccess: Boolean,
    @SerialName("response") val response: T,
    @SerialName("errorResponse") val errorResponse: ErrorResponse,
)

@Serializable
data class ErrorResponse(
    @SerialName("status") val status: Int,
    @SerialName("code") val code: String,
    @SerialName("message") val message: String
)