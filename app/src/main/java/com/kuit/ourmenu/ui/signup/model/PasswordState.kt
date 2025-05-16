package com.kuit.ourmenu.ui.signup.model

sealed class PasswordState {
    data object Default : PasswordState()
    data object NotMeetCondition : PasswordState()
    data object DifferentPassword : PasswordState()
    data object Valid : PasswordState()
}

private fun isValidPassword(password: String): Boolean {
    val regex = "^[a-zA-Z0-9]{8,}$".toRegex()
    return password.matches(regex)
}

fun checkPassword(password: String, confirmPassword: String): PasswordState {
    if (!isValidPassword(password)) {
        return PasswordState.NotMeetCondition
    }
    if (password != confirmPassword) {
        return PasswordState.DifferentPassword
    }
    return PasswordState.Valid
}