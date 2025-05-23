package com.kuit.ourmenu.ui.signup.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.repository.AuthRepository
import com.kuit.ourmenu.ui.oauth.KakaoModule.getUserEmail
import com.kuit.ourmenu.ui.signup.model.PasswordState
import com.kuit.ourmenu.ui.signup.model.SignupState
import com.kuit.ourmenu.ui.signup.uistate.SignupUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignupUiState())
    val uiState = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun updateDomain(domain: String) {
        _uiState.update {
            it.copy(domain = domain)
        }
    }

    fun updateCode(index: Int, code: String) {
        _uiState.update {
            it.copy(codes = it.codes.toMutableList().mapIndexed { i, item ->
                if (i == index) code.uppercase() else item
            })
        }
    }

    fun updatePassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.update {
            it.copy(confirmPassword = confirmPassword)
        }
    }

    fun setPasswordVisibility(visible: Boolean) {
        _uiState.update {
            it.copy(passwordVisible = visible)
        }
    }

    fun updatePasswordState(passwordState: PasswordState) {
        _uiState.update {
            it.copy(passwordState = passwordState)
        }
    }

    fun updateSelectedTime(index: Int) {
        _uiState.update {
            val selected = it.mealTimes[index - 6].selected
            it.copy(
                selectedTimes =
                    if (selected) it.selectedTimes.toMutableList() - it.mealTimes[index - 6].mealTime
                    else it.selectedTimes.toMutableList() + it.mealTimes[index - 6].mealTime,
                mealTimes = it.mealTimes.toMutableList()
                    .apply { this[index - 6].selected = !selected }
            )
        }
    }

    /* Api Section */
    fun sendEmail() {
        viewModelScope.launch {
            authRepository.sendEmail(
                email = "${_uiState.value.email}@${_uiState.value.domain}"
            )
                .fold(
                    onSuccess = {
                        _uiState.update {
                            it.copy(emailState = SignupState.Success)
                        }
                    },
                    onFailure = { error ->
                        _uiState.update {
                            it.copy(
                                emailState = SignupState.Error,
                                error = error.message ?: "Unknown error"
                            )
                        }
                    }

                )
            delay(1000)
            _uiState.update {
                it.copy(emailState = SignupState.Default)
            }
        }
    }

    fun verifyCode() {
        viewModelScope.launch {
            authRepository.confirmCode(
                confirmCode = _uiState.value.codes.joinToString(""),
                email = "${_uiState.value.email}@${_uiState.value.domain}"
            )
                .fold(
                    onSuccess = {
                        _uiState.update {
                            it.copy(verifyState = SignupState.Success)
                        }
                    },
                    onFailure = { error ->
                        _uiState.update {
                            it.copy(
                                verifyState = SignupState.Error,
                                error = error.message ?: "Unknown error"
                            )
                        }
                    }
                )
            delay(1000)
            _uiState.update {
                it.copy(verifyState = SignupState.Default)
            }
        }
    }

    fun signup() {
        if (_uiState.value.email == "" || _uiState.value.domain == "") {
            signupWithKakao()
        } else {
            signupWithEmail()
        }
    }

    private fun signupWithEmail() {
        viewModelScope.launch {
            val completeEmail = "${_uiState.value.email}@${_uiState.value.domain}"

            authRepository.signup(
                email = completeEmail,
                mealTime = _uiState.value.selectedTimes.map { it.substringBefore(":").toInt() },
                password = _uiState.value.password.takeIf { it.isNotEmpty() },
                signInType = SignInType.EMAIL
            ).fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(signupState = SignupState.Success)
                    }
                    Log.d("okhttp2", _uiState.value.signupState.toString())
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(signupState = SignupState.Error)
                    }
                    _uiState.update {
                        it.copy(error = error.message ?: "Unknown error")
                    }
                    Log.d("okhttp3", error.toString())
                }
            )
        }
    }

    private fun signupWithKakao() {
        viewModelScope.launch {
            val kakaoEmail = getUserEmail()

            authRepository.signup(
                email = kakaoEmail,
                mealTime = _uiState.value.selectedTimes.map { it.substringBefore(":").toInt() },
                password = _uiState.value.password.takeIf { it.isNotEmpty() },
                signInType = SignInType.KAKAO
            ).fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(signupState = SignupState.Success)
                    }
                    Log.d("okhttp2", _uiState.value.signupState.toString())
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(signupState = SignupState.Error)
                    }
                    _uiState.update {
                        it.copy(error = error.message ?: "Unknown error")
                    }
                    Log.d("okhttp3", error.toString())
                }
            )
        }
    }

}