package com.kuit.ourmenu.data.repository

import com.kuit.ourmenu.data.model.auth.request.LoginRequest
import com.kuit.ourmenu.data.service.AuthService
import com.kuit.ourmenu.utils.auth.TokenManager
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager
) {
    suspend fun login(loginRequest: LoginRequest): Result<Boolean> {
        return runCatching {
            val response = authService.login(loginRequest)
            if (response.isSuccess && response.response != null){
                tokenManager.saveAccessToken(response.response.accessToken)
                tokenManager.saveRefreshToken(response.response.refreshToken)
                true
            } else{
                throw Exception("로그인 실패: ${response.errorResponse?.message}")
            }
        }
    }
}