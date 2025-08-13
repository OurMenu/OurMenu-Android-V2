package com.kuit.ourmenu.data.model.user.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    @SerialName("announcementUrl")
    val announcementUrl: String,
    @SerialName("appReviewUrl")
    val appReviewUrl: String,
    @SerialName("customerServiceUrl")
    val customerServiceUrl: String,
    @SerialName("email")
    val email: String,
    @SerialName("mealTimeList")
    val mealTimeList: List<MealTime>,
    @SerialName("signInType")
    val signInType: String
) {
    @Serializable
    data class MealTime(
        @SerialName("isAfter")
        val isAfter: Boolean,
        @SerialName("mealTime")
        val mealTime: String
    )
}