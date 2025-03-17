package com.kuit.ourmenu.data.model.auth.request

import kotlinx.serialization.SerialName

data class LoginRequest(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
    @SerialName("signInType") val signInType: String
)
