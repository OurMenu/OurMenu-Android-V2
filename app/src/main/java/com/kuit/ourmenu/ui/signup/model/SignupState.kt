package com.kuit.ourmenu.ui.signup.model

sealed class SignupState {
    data object Default : SignupState()
    data object Loading : SignupState()
    data object Success : SignupState()
    data object Error : SignupState()
}