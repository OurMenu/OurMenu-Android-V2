package com.kuit.ourmenu.ui.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.repository.AuthRepository
import com.kuit.ourmenu.ui.onboarding.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Default)
    val loginState = _loginState.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun signInWithKakao() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            if(checkKakaoEmail()) {
                authRepository.login(
                    email = null,
                    password = null,
                    signInType = SignInType.KAKAO
                ).fold(
                    onSuccess = {
                        _loginState.value = LoginState.Success
                    },
                    onFailure = { error ->
                        _loginState.value = LoginState.Error
                        _error.value = error.message

                        delay(1000)
                        _loginState.value = LoginState.Default
                    }
                )
            }else{
                // 카카오 회원 X -> 회원가입
            }

        }
    }

    private suspend fun checkKakaoEmail(): Boolean {
        authRepository.checkKakaoEmail().fold(
            onSuccess = { response ->
                return response?.existUser == true
            },
            onFailure = { return false })
    }

    fun signInWithEmail() {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            authRepository.login(
                email = email.value,
                password = password.value,
                signInType = SignInType.EMAIL
            ).fold(
                onSuccess = {
                    _loginState.value = LoginState.Success
                },
                onFailure = { error ->
                    _loginState.value = LoginState.Error
                    _error.value = error.message

                    delay(1000)
                    _loginState.value = LoginState.Default
                }
            )


        }
    }

}

