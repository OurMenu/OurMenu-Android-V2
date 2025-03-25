package com.kuit.ourmenu.data.model.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangePasswordRequest(
    @SerialName("password")
    val password: String,
    @SerialName("newPassword")
    val newPassword: String
)