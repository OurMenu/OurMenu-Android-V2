package com.kuit.ourmenu.ui.onboarding.state

sealed class LoginState {
    data object Default: LoginState()
    data object Loading : LoginState()
    data object NotFoundUser : LoginState()
    data object DifferentPassword : LoginState()
    data object Success : LoginState()
    data object Error : LoginState()
}