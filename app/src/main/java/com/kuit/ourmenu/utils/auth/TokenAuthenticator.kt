package com.kuit.ourmenu.utils.auth

import com.kuit.ourmenu.data.service.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenManager: TokenManager,
    private val authService: AuthService
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val refreshToken = tokenManager.getRefreshToken().first()

            if (refreshToken.isNullOrEmpty()){
                tokenManager.clearToken()
                return@runBlocking null
            }
            val newTokenResponse = withContext(Dispatchers.IO) {
                try {
                    authService.reIssueToken(refreshToken)
                } catch (e: Exception) {
                    null
                }
            }

            if (newTokenResponse == null || !newTokenResponse.isSuccess || newTokenResponse.response == null) {
                tokenManager.clearToken()
                // TODO: 로그아웃 처리
                return@runBlocking null
            }

            val newAccessToken = newTokenResponse.response.accessToken
            val newRefreshToken = newTokenResponse.response.refreshToken
            tokenManager.saveAccessToken(newAccessToken) // 새 access token 저장
            tokenManager.saveRefreshToken(newRefreshToken) // 새 refresh token 저장

            // 기존 요청을 새로운 Access Token으로 다시 실행
            return@runBlocking response.request.newBuilder()
                .header("Authorization", "Bearer $newAccessToken")
                .build()
        }
    }

}