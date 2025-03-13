package com.kuit.ourmenu.data.model.auth.response

import kotlinx.serialization.SerialName

data class LoginResponse(
    @SerialName ("grantType") val grantType: String,
    @SerialName ("accessToken") val accessToken: String,
    @SerialName ("refreshToken") val refreshToken: String,
    @SerialName ("refreshTokenExpiredAt") val refreshTokenExpiredAt: String,
)
