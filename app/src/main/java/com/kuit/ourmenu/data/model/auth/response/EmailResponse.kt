package com.kuit.ourmenu.data.model.auth.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmailResponse(
    @SerialName("code")
    val code: String,
)