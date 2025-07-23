package com.kuit.ourmenu.data.model.user.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChangeMealTimeRequest(
    @SerialName("mealTime")
    val mealTime: List<String>
)