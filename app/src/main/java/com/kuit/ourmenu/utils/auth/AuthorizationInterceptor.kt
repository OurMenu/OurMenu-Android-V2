package com.kuit.ourmenu.utils.auth

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = runBlocking { tokenManager.getAccessToken() }
        val request = chain.request().newBuilder()

        accessToken.let {
            // header에 토큰을 추가
            request.addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(request.build())
    }
}