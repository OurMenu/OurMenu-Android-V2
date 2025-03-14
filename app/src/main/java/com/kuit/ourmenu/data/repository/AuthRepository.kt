package com.kuit.ourmenu.data.repository

import android.util.Log
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.model.auth.request.ConfirmCodeRequest
import com.kuit.ourmenu.data.model.auth.request.EmailRequest
import com.kuit.ourmenu.data.model.auth.request.LoginRequest
import com.kuit.ourmenu.data.model.auth.request.SignupRequest
import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.service.AuthService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authService: AuthService,
) {
    suspend fun signup(
        email: String?,
        mealTime: List<Int>,
        password: String?,
        signInType: SignInType
    ) = runCatching {
        Log.d("okhttp4", "sdf")
        val request = SignupRequest(
            email = email,
            mealTime = mealTime,
            password = password,
            signInType = signInType.name
        )
        Log.d("okhttp5", request.toString())
        authService.signup(
            request
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun logout() = runCatching {
        authService.logout().handleBaseResponse().getOrThrow()
    }

    suspend fun login(
        email: String,
        password: String?,
        signInType: SignInType
    ) = runCatching {
        // TODO : Set Token
        authService.login(
            LoginRequest(
                email = email,
                password = password,
                signInType = signInType.name
            )
        ).handleBaseResponse().getOrThrow()
    }

    suspend fun checkKakaoEmail(
        email: String?
    ) = runCatching {
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