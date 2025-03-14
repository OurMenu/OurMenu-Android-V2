package com.kuit.ourmenu.data.repository

import android.content.Context
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.model.auth.request.ConfirmCodeRequest
import com.kuit.ourmenu.data.model.auth.request.EmailRequest
import com.kuit.ourmenu.data.model.auth.request.LoginRequest
import com.kuit.ourmenu.data.model.auth.request.SignupRequest
import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.oauth.KakaoModule.getKakaoLogin
import com.kuit.ourmenu.data.oauth.KakaoModule.getUserEmail
import com.kuit.ourmenu.data.service.AuthService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authService: AuthService,
    @ApplicationContext private val context: Context
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

    suspend fun logout() = runCatching {
        authService.logout().handleBaseResponse().getOrThrow()
    }

    suspend fun login(
        email: String?,
        password: String?,
        signInType: SignInType
    ) = runCatching {

        authService.login(
            LoginRequest(
                email = if (email.isNullOrEmpty()) getUserEmail()!! else email,
                password = password,
                signInType = signInType.name
            )
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun checkKakaoEmail() = runCatching {
        getKakaoLogin(context)

        val email = getUserEmail()
        authService.checkKakaoEmail(
            EmailRequest(
                email = email
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
        authService.sendEmail(
            EmailRequest(
                email = email
            )
        ).handleBaseResponse().getOrThrow()
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