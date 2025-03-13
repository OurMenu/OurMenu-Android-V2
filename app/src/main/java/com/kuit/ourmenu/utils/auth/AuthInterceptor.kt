package com.kuit.ourmenu.utils.auth

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val accessToken = runBlocking { tokenManager.getAccessToken() }

        accessToken.let {
            // header에 토큰을 추가
            request
                .addHeader("Authorization", "Bearer $it")
        }
        return chain.proceed(request.build())
    }
}