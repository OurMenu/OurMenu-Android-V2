package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.service.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authService: AuthService
) {
    suspend fun signup(
        email: String,
        mealTime: List<Int>,
        password: String,
        name: String
    ) = runCatching {
        authService.signup(
            SignupRequest(
                email = email,
                mealTime = mealTime,
                password = password,
                signInType = name
            )
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun login(
        email: String,
        password: String,
        name: String
    ) = runCatching {
        authService.login(
            LoginRequest(
                email = email,
                password = password,
                signInType = name
            )
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun reissueToken(
        refreshToken: String
    ) = runCatching {
        authService.reissueToken(refreshToken).handleBaseResponse().getOrThrow()
    }

    suspend fun sendEmail(
        email: String
    ) = runCatching {
        authService.sendEmail(email).handleBaseResponse().getOrThrow()
    }

    suspend fun sendTemporaryPassword(
        email: String
    ) = runCatching {
        authService.sendTemporaryPassword(email).handleBaseResponse().getOrThrow()
    }

    suspend fun confirmCode(
        confirmCode: String,
        email: String
    ) = runCatching {
        authService.confirmCode(
            ConfirmCodeRequest(
                confirmCode = confirmCode,
                email = email
            )
        ).handleBaseResponse().getOrThrow()
    }
}