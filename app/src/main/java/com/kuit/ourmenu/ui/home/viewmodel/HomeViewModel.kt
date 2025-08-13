package com.kuit.ourmenu.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.model.base.OurMenuApiFailureException
import com.kuit.ourmenu.data.model.home.response.HomeQuestionResponse
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
    private val _questionState = MutableStateFlow<HomeQuestionResponse?>(null)
    val questionState = _questionState.asStateFlow()

    private val _home = MutableStateFlow(HomeResponse())
    val home = _home.asStateFlow()

    private val _showDialog = MutableStateFlow(true)
    val showDialog = _showDialog.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchQuestion()
    }

    fun refreshQuestion() {
        fetchQuestion()
    }

    fun selectAnswer(answer: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            homeRepository.postHomeAnswer(answer)
                .fold(
                    onSuccess = {
                        getHome()
                    },
                    onFailure = { throwable ->
                        _error.value = throwable.message ?: "답변 제출 중 오류가 발생했습니다."
                        onDialogDismiss()
                        _isLoading.value = false
                    }
                )

            _isLoading.value = false
        }
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    private fun fetchQuestion() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            homeRepository.postHomeQuestion()
                .fold(
                    onSuccess = { response ->
                        _questionState.value = response
                        if (response == null) {
                            onDialogDismiss()
                            getHome()
                        } else {
                            _isLoading.value = false
                        }
                    },
                    onFailure = { throwable ->
                        handleFailure(throwable)
                        _isLoading.value = false
                    }
                )
        }
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
                        onDialogDismiss()
                        _isLoading.value = false
                    },
                    onFailure = { throwable ->
                        handleFailure(throwable)
                        _isLoading.value = false
                    }
                )
        }
    }

    private fun handleFailure(throwable: Throwable) {
        when (throwable) {
            is OurMenuApiFailureException -> {
                if (throwable.code == "H400") {
                    _showDialog.value = true
                    fetchQuestion()
                } else {
                    _error.value = throwable.message ?: "알 수 없는 서버 오류입니다."
                    onDialogDismiss()
                }
            }
            else -> {
                _error.value = "네트워크 연결을 확인해주세요."
                onDialogDismiss()
            }
        }
    }
}