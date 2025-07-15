package com.kuit.ourmenu.ui.my.viewmodel

import com.kuit.ourmenu.data.model.auth.SignInType

data class MyPageUiState(
    val email: String = "",
    val signInType: SignInType = SignInType.EMAIL,
    val mealTimes: List<UserMealTime> = emptyList(),
    val error: String = "",
    val bottomSheetVisible: Boolean = false,
    val showCurrentPasswordModal: Boolean = false,
    val showNewPasswordModal: Boolean = false,
    val showLogoutModal: Boolean = false,
    val showDeleteAccountModal: Boolean = false,
    val isLogoutSuccess: Boolean = false,
    val isDeleteAccountSuccess: Boolean = false,
    val announcementUrl: String = "",
    val customerServiceUrl: String = "",
    val appReviewUrl: String = "",
)

data class UserMealTime(
    val mealTime: Int,
    val isAfter: Boolean = false,
)