package com.kuit.ourmenu.ui.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.model.base.OurMenuApiFailureException
import com.kuit.ourmenu.data.repository.AuthRepository
import com.kuit.ourmenu.ui.onboarding.model.LoginUiState
import com.kuit.ourmenu.ui.onboarding.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun updatePasswordVisible(isPasswordVisible: Boolean) {
        _uiState.update {
            it.copy(isPasswordVisible = isPasswordVisible)
        }
    }

    fun signInWithEmail() {
        viewModelScope.launch {
            _uiState.update { it.copy(loginState = LoginState.Loading) }

            authRepository.login(
                email = _uiState.value.email,
                password = _uiState.value.password,
                signInType = SignInType.EMAIL
            ).fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(loginState = LoginState.Success)
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(loginState = LoginState.Error)
                    }
                    when (error) {
                        is OurMenuApiFailureException -> {
                            _uiState.update { it.copy(error = error.message ?: "Unknown error") }
                            when (error.status) {
                                401 -> _uiState.update {
                                    it.copy(loginState = LoginState.DifferentPassword)
                                }

                                404 -> _uiState.update {
                                    it.copy(loginState = LoginState.NotFoundUser)
                                }
                            }
                        }

                        else -> _uiState.update {
                            it.copy(
                                error = error.message ?: "Unknown error"
                            )
                        }
                    }

                    delay(1000)
                    _uiState.update {
                        it.copy(loginState = LoginState.Default)
                    }
                }
            )


        }
    }

}