package com.kuit.ourmenu.ui.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.repository.AuthRepository
import com.kuit.ourmenu.ui.oauth.KakaoModule.getUserEmail
import com.kuit.ourmenu.ui.onboarding.state.KakaoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _kakaoState = MutableStateFlow<KakaoState>(KakaoState.Default)
    val kakaoState = _kakaoState.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    init {
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e("KakaoModule", "연결 끊기 실패", error)
            } else {
                Log.i("KakaoModule", "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }

    fun updateKakaoState(state: KakaoState) {
        _kakaoState.value = state
    }

    fun signInWithKakao() {
        viewModelScope.launch {
            val email = getUserEmail()
            email ?: run {
                _error.value = "이메일을 가져오는데 실패했습니다."
                return@launch
            }
            _kakaoState.value = KakaoState.Loading

            if (checkKakaoEmail()) {
                authRepository.login(
                    email = email,
                    password = null,
                    signInType = SignInType.KAKAO
                ).fold(
                    onSuccess = {
                        _kakaoState.value = KakaoState.Login
                    },
                    onFailure = { error ->
                        _kakaoState.value = KakaoState.Error
                        _error.value = error.message

                        delay(1000)
                    }
                )
            } else {
                // 카카오 회원 X -> 회원가입
                _kakaoState.value = KakaoState.Signup
            }

        }
    }

    private suspend fun checkKakaoEmail(): Boolean {
        val email = getUserEmail()

        authRepository.checkKakaoEmail(email).fold(
            onSuccess = { response ->
                return response?.existUser == true
            },
            onFailure = { return false }
        )
    }

}