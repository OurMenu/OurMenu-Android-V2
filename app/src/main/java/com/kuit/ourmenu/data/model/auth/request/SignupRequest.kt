package com.kuit.ourmenu.data.model.auth.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    @SerialName("email")
    val email: String,
    @SerialName("mealTime")
    val mealTime: List<Int>,
    @SerialName("password")
    val password: String,
    @SerialName("signInType")
    val signInType: String
)