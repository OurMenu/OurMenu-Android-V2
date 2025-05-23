package com.kuit.ourmenu.ui.my.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(MyPageUiState())
    val uiState = _uiState.asStateFlow()

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