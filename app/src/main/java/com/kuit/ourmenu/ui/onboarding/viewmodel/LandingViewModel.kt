package com.kuit.ourmenu.ui.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.user.UserApiClient
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.repository.AuthRepository
import com.kuit.ourmenu.ui.oauth.KakaoModule.getUserEmail
import com.kuit.ourmenu.ui.onboarding.model.LandingUiState
import com.kuit.ourmenu.ui.onboarding.state.KakaoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LandingUiState())
    val uiState = _uiState.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    init {
        // TODO : 디버깅 후 블록 삭제
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e("KakaoModule", "연결 끊기 실패", error)
            } else {
                Log.i("KakaoModule", "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }

    fun updateKakaoState(state: KakaoState) {
        _uiState.update {
            it.copy(kakaoState = state)
        }
    }

    fun signInWithKakao() {
        viewModelScope.launch {
            val email = getUserEmail()
            email ?: run {
                _uiState.update {
                    it.copy(error = "이메일을 가져오는데 실패했습니다.")
                }
                return@launch
            }
            _uiState.update {
                it.copy(kakaoState = KakaoState.Loading)
            }

            if (checkKakaoEmail()) {
                authRepository.login(
                    email = email,
                    password = null,
                    signInType = SignInType.KAKAO
                ).fold(
                    onSuccess = {
                        _uiState.update {
                            it.copy(kakaoState = KakaoState.Login)
                        }
                    },
                    onFailure = { error ->
                        _uiState.update {
                            it.copy(
                                kakaoState = KakaoState.Error,
                                error = error.message ?: "로그인 실패"
                            )
                        }

                        delay(1000)
                    }
                )
            } else {
                // 카카오 회원 X -> 회원가입
                _uiState.update {
                    it.copy(kakaoState = KakaoState.Signup)
                }
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