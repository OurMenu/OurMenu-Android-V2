package com.kuit.ourmenu.data.model.account.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("grantType")
    val grantType: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("refreshTokenExpiredAt")
    val refreshTokenExpiredAt: String
)