package com.kuit.ourmenu.ui.onboarding.model

import com.kuit.ourmenu.ui.onboarding.state.LoginState

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loginState: LoginState = LoginState.Default,
    val isPasswordVisible: Boolean = false,
    val error: String = ""
)
