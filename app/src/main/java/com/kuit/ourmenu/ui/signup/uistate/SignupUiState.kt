package com.kuit.ourmenu.ui.signup.uistate

import com.kuit.ourmenu.ui.signup.model.PasswordState
import com.kuit.ourmenu.ui.signup.model.SignupState

data class SignupUiState(
    val email: String = "",
    val domain: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val passwordVisible: Boolean = false,
    val codes: List<String> = listOf("", "", "", "", "", ""),
    val mealTimes: List<MealTime> = List(18) {
        MealTime(mealTime = "${it + 6}:00")
    },
    val selectedTimes: List<String> = emptyList(),
    val emailState: SignupState = SignupState.Default,
    val verifyState: SignupState = SignupState.Default,
    val passwordState: PasswordState = PasswordState.Default,
    val signupState: SignupState = SignupState.Default,
    val error: String = ""
)

data class MealTime(
    val mealTime: String,
    var selected: Boolean = false
)