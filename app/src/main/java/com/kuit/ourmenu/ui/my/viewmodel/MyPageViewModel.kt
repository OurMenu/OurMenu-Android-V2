package com.kuit.ourmenu.ui.my.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.oauth.KakaoRepository
import com.kuit.ourmenu.data.repository.AuthRepository
import com.kuit.ourmenu.data.repository.UserRepository
import com.kuit.ourmenu.ui.common.model.PasswordState
import com.kuit.ourmenu.ui.common.model.checkPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    private val kakaoRepository: KakaoRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState = _uiState.asStateFlow()

    fun getUserInfo() {
        viewModelScope.launch {
            userRepository.getUserInfo().fold(
                onSuccess = { response ->
                    if (response != null) {
                        _uiState.update {
                            it.copy(
                                email = response.email,
                                mealTimes = response.mealTimeList.map { mealTime ->
                                    UserMealTime(
                                        mealTime = mealTime.mealTime.substring(0, 2).toInt(),
                                        isAfter = mealTime.isAfter,
                                    )
                                },
                                signInType = SignInType.valueOf(response.signInType),
                                announcementUrl = response.announcementUrl,
                                customerServiceUrl = response.customerServiceUrl,
                                appReviewUrl = response.appReviewUrl,
                            )
                        }
                    } else {
                        Log.d("MyPageViewModel", "getUserInfo: response is null")
                    }
                },
                onFailure = {
                    Log.d("MyPageViewModel", "getUserInfo: $it")
                }
            )
        }
    }

    fun updatePasswordState(
        passwordState: PasswordState?
    ) {
        val newPasswordState = passwordState ?: checkPassword(
            password = uiState.value.newPassword,
            confirmPassword = uiState.value.confirmNewPassword,
        )

        _uiState.update {
            it.copy(passwordState = newPasswordState)
        }
    }

    fun changePassword() {
        viewModelScope.launch {
            userRepository.changePassword(
                currentPassword = uiState.value.currentPassword,
                newPassword = uiState.value.newPassword,
            ).fold(
                onSuccess = { response ->
                    if (response != null) {
                        Log.d("MyPageViewModel", "changePassword: $response")
                    } else {
                        Log.d("MyPageViewModel", "changePassword: response is null")
                    }
                    updateNewPasswordModalVisible(false)
                    updateShowCompleteState(true)
                },
                onFailure = {
                    Log.d("MyPageViewModel", "changePassword: $it")
                    updatePasswordState(PasswordState.IncorrectPassword)
                }
            )
        }
    }

    fun updateShowCompleteState(visible: Boolean) {
        _uiState.update {
            it.copy(showCompleteSnackbar = visible)
        }
    }

    fun logout(
        context: Context
    ) {
        viewModelScope.launch {
            var kakaoResult = true

            runBlocking {
                kakaoRepository.logout(
                    errorLogout = {
                        Log.d("MyPageViewModel", "Kakao logout failed: $it")
                        kakaoResult = false
                    },
                    successLogout = {
                        Log.d("MyPageViewModel", "Kakao logout success")
                        kakaoResult = true
                    }
                )
            }

            if (kakaoResult) {
                authRepository.logout().fold(
                    onSuccess = {
                        Log.d("MyPageViewModel", "logout Success: $it")
                        setLogoutSuccess()
                    },
                    onFailure = {
                        Log.d("MyPageViewModel", "logout Failure: $it")
                        kakaoRepository.getKakaoLogin(
                            context = context,
                        ) { }
                    }
                )
            }
        }
    }

    fun deleteAccount(
        context: Context
    ) {
        viewModelScope.launch {
            var kakaoResult = true

            runBlocking {
                kakaoRepository.unlink(
                    errorUnlink = {
                        Log.d("MyPageViewModel", "Kakao unlink failed: $it")
                        kakaoResult = false
                    },
                    successUnlink = {
                        Log.d("MyPageViewModel", "Kakao unlink success")
                        kakaoResult = true
                    }
                )
            }
            if (kakaoResult) {
                userRepository.deleteUser().fold(
                    onSuccess = {
                        Log.d("MyPageViewModel", "deleteAccount: $it")
                        setDeleteAccountSuccess()
                    },
                    onFailure = {
                        Log.d("MyPageViewModel", "deleteAccount: $it")
                        kakaoRepository.getKakaoLogin(
                            context = context
                        ) { }
                    }
                )
            }
        }
    }

    fun updateBottomSheetVisible(visible: Boolean) {
        _uiState.update {
            it.copy(bottomSheetVisible = visible)
        }
    }

    fun updateCurrentPasswordModalVisible(visible: Boolean) {
        if (!visible) {
            _uiState.update {
                it.copy(
                    isPasswordViewVisible = false,
                    passwordState = PasswordState.Default,
                    showCurrentPasswordModal = false,
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    showCurrentPasswordModal = true,
                )
            }
        }
    }

    fun updateNewPasswordModalVisible(visible: Boolean) {
        if (!visible) {
            _uiState.update {
                it.copy(
                    currentPassword = "",
                    newPassword = "",
                    confirmNewPassword = "",
                    isPasswordViewVisible = false,
                    passwordState = PasswordState.Default,
                    showNewPasswordModal = false,
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    showNewPasswordModal = true,
                )
            }
        }
    }

    fun setLogoutSuccess() {
        updateLogoutModalVisible(false)
        _uiState.update {
            it.copy(isLogoutSuccess = true)
        }
    }

    fun setDeleteAccountSuccess() {
        updateDeleteAccountModalVisible(false)
        _uiState.update {
            it.copy(isDeleteAccountSuccess = true)
        }
    }

    fun updateLogoutModalVisible(visible: Boolean) {
        _uiState.update {
            it.copy(showLogoutModal = visible)
        }
    }

    fun updateDeleteAccountModalVisible(visible: Boolean) {
        _uiState.update {
            it.copy(showDeleteAccountModal = visible)
        }
    }

    fun updateCurrentPassword(currentPassword: String) {
        _uiState.update {
            it.copy(currentPassword = currentPassword)
        }
    }

    fun updateNewPassword(newPassword: String) {
        _uiState.update {
            it.copy(newPassword = newPassword)
        }
    }

    fun updateConfirmNewPassword(confirmNewPassword: String) {
        _uiState.update {
            it.copy(confirmNewPassword = confirmNewPassword)
        }
    }

    fun updatePasswordVisibility() {
        _uiState.update {
            it.copy(isPasswordViewVisible = !it.isPasswordViewVisible)
        }
    }

    fun reInputCurrentPassword() {
        updateCurrentPasswordModalVisible(true)
        updateNewPasswordModalVisible(false)
    }
}