package com.kuit.ourmenu.utils.auth

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kuit.ourmenu.BuildConfig
import com.kuit.ourmenu.data.model.auth.response.TokenReIssueResponse
import com.kuit.ourmenu.data.service.AuthService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            tokenManager.getRefreshToken().first()
        }
        return runBlocking {
            if (refreshToken.isNullOrEmpty()){
                return@runBlocking null
            }
            val newToken = getNewToken(refreshToken)

            if (newToken == null) {
                tokenManager.clearToken()
            }

            newToken?.let {
                tokenManager.saveAccessToken(it.accessToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${it.accessToken}")
                    .build()
            }
        }
    }

    private suspend fun getNewToken(refreshToken: String): TokenReIssueResponse? {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                Json.asConverterFactory(requireNotNull("application/json".toMediaType()))
            )
            .client(okHttpClient)
            .build()
        val service = retrofit.create(AuthService::class.java)
        return service.reIssueToken(refreshToken).response
    }

}