package com.kuit.ourmenu.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.home.response.HomeResponse
import com.kuit.ourmenu.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    private val _home = MutableStateFlow(HomeResponse())
    val home = _home.asStateFlow()

    private val _showDialog = MutableStateFlow(true)
    val showDialog = _showDialog.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getHome()
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun getHome() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            homeRepository.getHome()
                .fold(
                    onSuccess = { response ->
                        if (response != null) {
                            _home.value = response
                        }
                    },
                    onFailure = { throwable ->
                        _error.value = throwable.message ?: "홈을 불러오는 중 오류가 발생했습니다."
                    }
                )

            _isLoading.value = false
        }
    }
}