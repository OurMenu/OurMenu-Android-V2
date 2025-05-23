package com.kuit.ourmenu.ui.my.viewmodel

import com.kuit.ourmenu.ui.signup.uistate.MealTime

data class MyPageUiState(
    val email: String = "",
    val password: String = "",
    val mealTimes: List<MealTime> = List(18) {
        MealTime(mealTime = "${it + 6}:00")
    },
    val selectedTimes: List<String> = emptyList(),
    val error: String = "",
    val bottomSheetVisible: Boolean = false,
    val showCurrentPasswordModal: Boolean = false,
    val showNewPasswordModal: Boolean = false,
    val showLogoutModal: Boolean = false,
    val showDeleteAccountModal: Boolean = false
)
