package com.kuit.ourmenu.ui.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kuit.ourmenu.data.repository.CacheRepository
import com.kuit.ourmenu.ui.onboarding.mapper.toState
import com.kuit.ourmenu.ui.onboarding.state.CacheState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val cacheRepository: CacheRepository
) : ViewModel() {

    private val _cacheInfoData = MutableStateFlow(CacheState())
    val cacheInfoData = _cacheInfoData.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        getCacheData()
    }

    private fun getCacheData() {
        viewModelScope.launch {
            cacheRepository.getCacheData().fold(
                onSuccess = { data ->
                    _cacheInfoData.value = data?.toState() ?: CacheState()
                },
                onFailure = {
                    _errorMessage.value = it.message
                }
            )
        }
    }

}