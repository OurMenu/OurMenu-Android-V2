package com.kuit.ourmenu.data.model.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TemporaryPasswordResponse(
    @SerialName("temporaryPassword")
    val temporaryPassword: String,
)