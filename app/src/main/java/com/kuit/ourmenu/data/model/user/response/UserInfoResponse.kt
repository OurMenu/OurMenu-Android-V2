package com.kuit.ourmenu.data.model.user.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("email")
    val email: String,
    @SerialName("mealTime")
    val mealTime: List<Int>,
    @SerialName("signInType")
    val signInType: String
)