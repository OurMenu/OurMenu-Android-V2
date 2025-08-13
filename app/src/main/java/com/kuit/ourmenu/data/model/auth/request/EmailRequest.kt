package com.kuit.ourmenu.data.model.auth.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmailRequest(
    @SerialName("email")
    val email: String?,
)