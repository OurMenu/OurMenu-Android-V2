package com.kuit.ourmenu.data.model.account.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmailResponse(
    @SerialName("code")
    val code: String,
)