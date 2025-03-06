package com.kuit.ourmenu.data.model.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReissueTokenResponse(
    @SerialName("accessToken")
    val accessToken: String,
    @SerialName("grantType")
    val grantType: String,
    @SerialName("refreshToken")
    val refreshToken: String,
    @SerialName("refreshTokenExpiredAt")
    val refreshTokenExpiredAt: String
)