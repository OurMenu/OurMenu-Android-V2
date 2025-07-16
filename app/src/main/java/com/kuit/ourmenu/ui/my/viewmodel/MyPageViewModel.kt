package com.kuit.ourmenu.ui.my.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.model.base.OurMenuApiFailureException
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
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
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

    fun logout() {
        viewModelScope.launch {
            authRepository.logout().fold(
                onSuccess = {
                    updateLogoutModalVisible(false)
                    Log.d("MyPageViewModel", "logout: $it")
                },
                onFailure = {
                    Log.d("MyPageViewModel", "logout: $it")
                }
            )
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            userRepository.deleteUser().fold(
                onSuccess = {
                    updateDeleteAccountModalVisible(false)
                    Log.d("MyPageViewModel", "deleteAccount: $it")
                },
                onFailure = {
                    Log.d("MyPageViewModel", "deleteAccount: $it")
                }
            )
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