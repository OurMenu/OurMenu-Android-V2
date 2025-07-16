package com.kuit.ourmenu.data.oauth

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume

@Singleton
class KakaoRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getKakaoLogin(
        successLogin: () -> Unit,
    ) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("KakaoModule", "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i("KakaoModule", "카카오계정으로 로그인 성공 ${token.accessToken}")
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e("KakaoModule", "카카오톡으로 로그인 실패", error)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i("KakaoModule", "카카오톡으로 로그인 성공 ${token.accessToken}")
                }
                successLogin()
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            successLogin()
        }
    }

    suspend fun getUserEmail(): String? {
        if (AuthApiClient.instance.hasToken()) {
            return suspendCancellableCoroutine { continuation ->
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e("KakaoModule", "사용자 정보 요청 실패", error)
                        continuation.resume(null)
                    } else if (user != null) {
                        val email = user.kakaoAccount?.email
                        Log.e("KakaoModule2", email.toString())
                        continuation.resume(email)
                    } else {
                        continuation.resume(null)
                    }
                }
            }
        } else {// has no token
            Log.e("KakaoModule", "토큰 실패")
            return null
        }
    }

    fun logout(
        successLogout: () -> Unit,
        errorLogout: (Throwable) -> Unit,
    ) {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.logout { error ->
                if (error != null) {
                    Log.e("KakaoTag", "로그아웃 실패. SDK에서 토큰 삭제됨", error)
                    errorLogout(error)
                } else {
                    Log.i("KakaoTag", "로그아웃 성공. SDK에서 토큰 삭제됨")
                    successLogout()
                }
            }
        }
    }

    fun unlink(
        successUnlink: () -> Unit,
        errorUnlink: (Throwable) -> Unit,
    ) {
        if (AuthApiClient.instance.hasToken()) {
            UserApiClient.instance.unlink { error ->
                if (error != null) {
                    Log.e("KakaoTag", "회원 탈퇴 실패. SDK에서 토큰 삭제됨", error)
                    errorUnlink(error)
                } else {
                    Log.i("KakaoTag", "회원 탈퇴 성공. SDK에서 토큰 삭제됨")
                    successUnlink()
                }
            }
        }
    }
}