package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.account.request.ConfirmCodeRequest
import com.kuit.ourmenu.data.model.account.request.LoginRequest
import com.kuit.ourmenu.data.model.account.request.SignupRequest
import com.kuit.ourmenu.data.model.base.handleBaseResponse
import com.kuit.ourmenu.data.service.AccountService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(
    private val accountService: AccountService
) {
    suspend fun signup(
        email: String,
        mealTime: List<Int>,
        password: String,
        name: String
    ) = runCatching {
        accountService.signup(
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
        accountService.login(
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
        accountService.reissueToken(refreshToken).handleBaseResponse().getOrThrow()
    }

    suspend fun sendEmail(
        email: String
    ) = runCatching {
        accountService.sendEmail(email).handleBaseResponse().getOrThrow()
    }

    suspend fun sendTemporaryPassword(
        email: String
    ) = runCatching {
        accountService.sendTemporaryPassword(email).handleBaseResponse().getOrThrow()
    }

    suspend fun confirmCode(
        confirmCode: String,
        email: String
    ) = runCatching {
        accountService.confirmCode(
            ConfirmCodeRequest(
                confirmCode = confirmCode,
                email = email
            )
        ).handleBaseResponse().getOrThrow()
    }
}