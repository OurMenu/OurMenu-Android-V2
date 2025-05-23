package com.kuit.ourmenu.ui.my.viewmodel

import android.R.attr.data
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.auth.SignInType
import com.kuit.ourmenu.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserRepository,
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
                                mealTimes = response.mealTime.map { it.substring(3, 5).toInt() }, // TODO: 리스폰스 변경에 따라 수정해야함
                                signInType = SignInType.valueOf(response.signInType),
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

    fun updateBottomSheetVisible(visible: Boolean) {
        _uiState.update {
            it.copy(bottomSheetVisible = visible)
        }
    }

    fun updateCurrentPasswordModalVisible(visible: Boolean) {
        _uiState.update {
            it.copy(showCurrentPasswordModal = visible)
        }
    }

    fun updateNewPasswordModalVisible(visible: Boolean) {
        _uiState.update {
            it.copy(showNewPasswordModal = visible)
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

}