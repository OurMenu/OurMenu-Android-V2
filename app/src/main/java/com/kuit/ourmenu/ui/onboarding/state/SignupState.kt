package com.kuit.ourmenu.ui.onboarding.state

sealed class SignupState {
    data object Default : SignupState()
    data object Loading : SignupState()
    data object Success : SignupState()
    data object Error : SignupState()
}