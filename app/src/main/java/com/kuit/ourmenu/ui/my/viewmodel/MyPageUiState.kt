package com.kuit.ourmenu.ui.my.viewmodel

data class MyPageUiState(
    val email: String = "",
    val password: String = "",
    val mealTimes: List<String> = emptyList(),
    val selectedMealTimes: List<String> = emptyList(),
    val error: String = "",
    val bottomSheetVisible: Boolean = false,
    val showCurrentPasswordModal: Boolean = false,
    val showNewPasswordModal: Boolean = false,
    val showLogoutModal: Boolean = false,
    val showDeleteAccountModal: Boolean = false,
    val isLogoutSuccess: Boolean = false,
    val isDeleteAccountSuccess: Boolean = false,
)